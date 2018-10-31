package com.lincoln.adam.githubshopifylauncher.repo

import android.arch.lifecycle.ViewModel

data class RepoViewModel(
    val id: String,
    val name: String,
    val fork: String,
    val stargazersCount: String,
    val timeSinceCreated: String,
    val htmlUrl: String
) : ViewModel()
