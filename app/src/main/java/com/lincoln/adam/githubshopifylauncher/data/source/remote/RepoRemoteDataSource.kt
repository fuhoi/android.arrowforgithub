package com.lincoln.adam.githubshopifylauncher.data.source.remote

import com.lincoln.adam.githubshopifylauncher.data.RepoModel
import com.lincoln.adam.githubshopifylauncher.data.source.RepoDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepoRemoteDataSource : RepoDataSource {

    companion object {

        const val GITHUB_ORG_NAME = "shopify"

        private var INSTANCE: RepoRemoteDataSource? = null

        @JvmStatic
        fun getInstance(): RepoRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(RepoRemoteDataSource::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = RepoRemoteDataSource()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    private val repoCache = LinkedHashMap<Int, RepoModel>(0)

    @Inject
    lateinit var gitHubDeveloperApiService: GitHubDeveloperApiService

    init {
        DaggerGitHubDeveloperApiComponent.create().inject(this)
    }

    override fun getRepos(repoCallback: RepoDataSource.RepoCallback) {
        val call = gitHubDeveloperApiService.getRepos(GITHUB_ORG_NAME)
        call.enqueue(object : Callback<List<RepoModel>> {
            override fun onResponse(call: Call<List<RepoModel>>, response: Response<List<RepoModel>>) {
                val modelList = response.body()!!
                repoCallback.onLoaded(modelList)
            }

            override fun onFailure(call: Call<List<RepoModel>>, t: Throwable) {
                // TODO Implement onFailure
                TODO("not implemented")
            }
        })
    }

    override fun refreshRepos() {
        // N/A - Implemented by RepoRepository.
    }

    override fun saveRepo(repo: RepoModel) {
        repoCache[repo.id] = repo
    }

    override fun deleteAllRepos() {
        repoCache.clear()
    }

}
