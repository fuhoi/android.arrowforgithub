package com.lincoln.adam.githubshopifylauncher

import android.arch.lifecycle.ViewModel

data class RepoViewModel(val repoModel: RepoModel) : ViewModel() {
    val id: String = repoModel.id.toString()
    val name: String = repoModel.name
    val fork: String = "Fork: ${repoModel.fork}"
    val stargazersCount: String = "Stargazers: ${getStringFromNumber(repoModel)}"
    val timeSinceCreated: String = "Created: ${getTimeSinceCreated(repoModel)} ago"
    val htmlUrl: String = repoModel.html_url
}
