package com.adamlincoln.android.arrowforgithub.presentation.repo

interface RepoOnClickListener {

    fun onGitHubUrlClick(repo: RepoViewModel)

    fun onHomepageUrlClick(repo: RepoViewModel)

}