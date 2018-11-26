package com.adamlincoln.android.arrowforgithub.data.repo.source

import com.adamlincoln.android.arrowforgithub.data.repo.RepoModel

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