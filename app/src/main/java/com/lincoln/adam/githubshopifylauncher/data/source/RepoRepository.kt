package com.lincoln.adam.githubshopifylauncher.data.source

import com.lincoln.adam.githubshopifylauncher.data.RepoModel

class RepoRepository(
    private val repoRemoteDataSource: RepoDataSource,
    private val repoLocalDataSource: RepoDataSource
) : RepoDataSource {

    companion object {

        private var INSTANCE: RepoRepository? = null

        @JvmStatic fun getInstance(repoRemoteDataSource: RepoDataSource, repoLocalDataSource: RepoDataSource): RepoRepository {
            return INSTANCE ?: RepoRepository(repoRemoteDataSource, repoLocalDataSource).apply { INSTANCE = this }
        }
    }

    var cachedRepos: LinkedHashMap<Int, RepoModel> = LinkedHashMap()

    var cacheIsDirty = false

    override fun getRepos(repoCallback: RepoDataSource.RepoCallback) {
        println("getRepos - Start - cacheIsDirty: $cacheIsDirty")

        // Respond immediately with cache if available and not dirty
        if (cachedRepos.isNotEmpty() && !cacheIsDirty) {
            repoCallback.onLoaded(ArrayList(cachedRepos.values))
            println("getRepos - Returning from cache")
            return
        }

        if (cacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            println("getRepos - Calling getReposFromRemoteDataSource")
            getReposFromRemoteDataSource(repoCallback)
        } else {
            // Query the local storage if available. If not, query the network.
            println("getRepos - Checking repoLocalDataSource")
            repoLocalDataSource.getRepos(object : RepoDataSource.RepoCallback {
                override fun onLoaded(repoList: List<RepoModel>) {
                    refreshCache(repoList)
                    repoCallback.onLoaded(ArrayList(cachedRepos.values))
                }

                override fun onDataNotAvailable() {
                    getReposFromRemoteDataSource(repoCallback)
                }
            })
        }

        println("getRepos - End")
    }

    private fun refreshCache(repoList: List<RepoModel>) {
        cachedRepos.clear()
        repoList.forEach {
            cacheAndPerform(it) {}
        }
        cacheIsDirty = false
    }

    private inline fun cacheAndPerform(repoModel: RepoModel, perform: (RepoModel) -> Unit) {
        val cachedTask = RepoModel(repoModel.id, repoModel.name, repoModel.fork, repoModel.created_at, repoModel.stargazers_count, repoModel.html_url)
        cachedRepos.put(cachedTask.id, cachedTask)
        perform(cachedTask)
    }

    private fun getReposFromRemoteDataSource(repoCallback: RepoDataSource.RepoCallback) {
        println("getReposFromRemoteDataSource - Start")

        repoRemoteDataSource.getRepos(object : RepoDataSource.RepoCallback {
            override fun onLoaded(repoList: List<RepoModel>) {
                // repoCallback.onLoaded(ArrayList(repoList))

                refreshCache(repoList)
                refreshLocalDataSource(repoList)
                repoCallback.onLoaded(ArrayList(cachedRepos.values))
            }

            override fun onDataNotAvailable() {
                repoCallback.onDataNotAvailable()
            }
        })

        println("getReposFromRemoteDataSource - End")
    }

    private fun refreshLocalDataSource(repoList: List<RepoModel>) {
        repoLocalDataSource.deleteAllRepos()
        for (repo in repoList) {
            repoLocalDataSource.saveRepo(repo)
        }
    }

    override fun refreshRepos() {
        cacheIsDirty = true
    }

    override fun saveRepo(repo: RepoModel) {
        // Do in memory cache update to keep the app UI up to date
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