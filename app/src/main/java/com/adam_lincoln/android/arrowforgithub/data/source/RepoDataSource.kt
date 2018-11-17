package com.adam_lincoln.android.arrowforgithub.data.source

import com.adam_lincoln.android.arrowforgithub.data.RepoModel

interface RepoDataSource {

    interface RepoCallback {

        fun onError()

        fun onNoData()

        fun onLoaded(repoList: List<RepoModel>)

    }

    fun getRepos(repoCallback: RepoCallback)

    fun saveRepo(repo: RepoModel)

    fun refreshRepos()

    fun deleteAllRepos()

}