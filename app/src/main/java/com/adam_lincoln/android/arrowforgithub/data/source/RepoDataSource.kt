package com.adam_lincoln.android.arrowforgithub.data.source

import com.adam_lincoln.android.arrowforgithub.data.RepoModel

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