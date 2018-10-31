package com.lincoln.adam.githubshopifylauncher

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainRemoteDataSource : MainDataSource {

    override fun getTasks(callback: MainDataSource.LoadTasksCallback) {

        val GITHUB_BASE_URL = "https://api.github.com/"
        val GITHUB_ORG_NAME = "shopify"

        val retrofit = Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)

        val name = GITHUB_ORG_NAME
        val call = service.listRepos(name)
        call.enqueue(object : Callback<List<RepoModel>> {
            override fun onResponse(call: Call<List<RepoModel>>, response: Response<List<RepoModel>>) {
                val modelList = response.body()!!
                callback.onTasksLoaded(modelList)
            }

            override fun onFailure(call: Call<List<RepoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun refreshTasks() {
        // N/A
    }
}