package com.lincoln.adam.githubshopifylauncher.data.source.remote

import com.lincoln.adam.githubshopifylauncher.data.RepoModel
import com.lincoln.adam.githubshopifylauncher.data.source.RepoDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoRemoteDataSource : RepoDataSource {

    companion object {

        const val GITHUB_BASE_URL = "https://api.github.com/"
        const val GITHUB_ORG_NAME = "shopify"

        private var INSTANCE: RepoRemoteDataSource? = null

        @JvmStatic fun getInstance(): RepoRemoteDataSource {
            return INSTANCE ?: RepoRemoteDataSource().apply { INSTANCE = this }
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(GitHubService::class.java)

    override fun getRepos(repoCallback: RepoDataSource.RepoCallback) {
        val call = service.getRepos(GITHUB_ORG_NAME)
        call.enqueue(object : Callback<List<RepoModel>> {
            override fun onResponse(call: Call<List<RepoModel>>, response: Response<List<RepoModel>>) {
                val modelList = response.body()!!
                repoCallback.onLoaded(modelList)
            }

            override fun onFailure(call: Call<List<RepoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun refreshRepos() {
        // N/A
    }
}