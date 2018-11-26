package com.adamlincoln.android.arrowforgithub.data.repo.source

import com.adamlincoln.android.arrowforgithub.data.repo.RepoModel

class RepoRepository(
    private val repoRemoteDataSource: RepoDataSource,
    private val repoLocalDataSource: RepoDataSource
) : RepoDataSource {

    companion object {

        private var INSTANCE: RepoRepository? = null

        @JvmStatic
        fun getInstance(repoRemoteDataSource: RepoDataSource, repoLocalDataSource: RepoDataSource): RepoRepository {
            if (INSTANCE == null) {
                synchronized(RepoRepository::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = RepoRepository(repoRemoteDataSource, repoLocalDataSource)
                    }
                }
            }
            return INSTANCE!!
        }
    }

    private val cachedRepos: LinkedHashMap<Int, RepoModel> = LinkedHashMap()

    private var cacheIsDirty = false

    override fun getRepos(repoCallback: RepoDataSource.RepoCallback) {
        // Respond immediately with cache if available and not dirty.
        if (cachedRepos.isNotEmpty() && !cacheIsDirty) {
            repoCallback.onLoaded(ArrayList(cachedRepos.values))
            return
        }

        if (cacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getReposFromRemoteDataSource(repoCallback)
        } else {
            // Query the local storage if available. If not, query the network.
            repoLocalDataSource.getRepos(object : RepoDataSource.RepoCallback {

                override fun onError() {
                    repoCallback.onError()
                }

                override fun onNoData() {
                    getReposFromRemoteDataSource(repoCallback)
                }

                override fun onLoaded(repoList: List<RepoModel>) {
                    refreshCache(repoList)
                    repoCallback.onLoaded(ArrayList(cachedRepos.values))
                }

            })
        }
    }

    private fun refreshCache(repoList: List<RepoModel>) {
        cachedRepos.clear()
        repoList.forEach { it -> cacheAndPerform(it) {} }
        cacheIsDirty = false
    }

    private inline fun cacheAndPerform(repoModel: RepoModel, perform: (RepoModel) -> Unit) {
        val cachedRepo = RepoModel(
            repoModel.id,
            repoModel.name,
            repoModel.description,
            repoModel.fork,
            repoModel.forks_count,
            repoModel.created_at,
            repoModel.stargazers_count,
            repoModel.html_url,
            repoModel.homepage,
            repoModel.language,
            repoModel.archived
        )
        cachedRepos[cachedRepo.id] = cachedRepo
        perform(cachedRepo)
    }

    private fun getReposFromRemoteDataSource(repoCallback: RepoDataSource.RepoCallback) {
        repoRemoteDataSource.getRepos(object : RepoDataSource.RepoCallback {

            override fun onError() {
                repoCallback.onError()
            }

            override fun onNoData() {
                repoCallback.onNoData()
            }

            override fun onLoaded(repoList: List<RepoModel>) {
                refreshCache(repoList)
                refreshLocalDataSource(repoList)
                repoCallback.onLoaded(ArrayList(cachedRepos.values))
            }

        })
    }

    private fun refreshLocalDataSource(repoList: List<RepoModel>) {
        repoLocalDataSource.deleteAllRepos()
        repoList.forEach { repo -> repoLocalDataSource.saveRepo(repo) }
    }

    override fun refreshRepos() {
        cacheIsDirty = true
    }

    override fun saveRepo(repo: RepoModel) {
        // Do in memory cache update to keep the app UI up to date.
        cacheAndPerform(repo) {
            repoRemoteDataSource.saveRepo(it)
            repoLocalDataSource.saveRepo(it)
        }
    }

    override fun deleteAllRepos() {
        repoRemoteDataSource.deleteAllRepos()
        repoLocalDataSource.deleteAllRepos()
        cachedRepos.clear()
    }
}