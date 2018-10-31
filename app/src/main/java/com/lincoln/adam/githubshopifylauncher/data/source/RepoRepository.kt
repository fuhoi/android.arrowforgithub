package com.lincoln.adam.githubshopifylauncher.data.source

import com.lincoln.adam.githubshopifylauncher.data.RepoModel

class RepoRepository(
    private val repoRemoteDataSource: RepoDataSource
    //,private val mainLocalDataSource: RepoDataSource?
) : RepoDataSource {

    companion object {

        private var INSTANCE: RepoRepository? = null

        @JvmStatic fun getInstance(repoRemoteDataSource: RepoDataSource /*, mainLocalDataSource: RepoDataSource*/): RepoRepository {
            return INSTANCE ?: RepoRepository(repoRemoteDataSource /*, mainLocalDataSource*/).apply { INSTANCE = this }
        }
    }

    override fun getRepos(repoCallback: RepoDataSource.RepoCallback) {
        getReposFromRemoteDataSource(repoCallback)
    }

    private fun getReposFromRemoteDataSource(repoCallback: RepoDataSource.RepoCallback) {
        repoRemoteDataSource.getRepos(object: RepoDataSource.RepoCallback {
            override fun onLoaded(repoList: List<RepoModel>) {
                repoCallback.onLoaded(ArrayList(repoList))
            }
        })
    }

    override fun refreshRepos() {
        // cacheIsDirty = true
    }
}