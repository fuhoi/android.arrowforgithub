package com.lincoln.adam.githubshopifylauncher.data.source

import com.lincoln.adam.githubshopifylauncher.data.RepoModel

interface RepoDataSource {

    interface RepoCallback {

        fun onLoaded(repoList: List<RepoModel>)

        fun onDataNotAvailable()

    }

    fun getRepos(repoCallback: RepoCallback)

    fun saveRepo(repo: RepoModel)

    fun refreshRepos()

    fun deleteAllRepos()

}