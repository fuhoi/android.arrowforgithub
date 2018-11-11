package com.adam_lincoln.android.arrowforgithub.presentation.repo

import android.arch.lifecycle.ViewModel

data class RepoViewModel(
    val id: String,
    val name: String,
    val description: String?,
    val fork: Boolean,
    val fork_text: String,
    val fork_count: String,
    val time_since_created: String,
    val stargazers_count: String,
    val github_url: String,
    val homepage_url: String?,
    val language: String?,
    val archived: Boolean,
    val archived_text: String
) : ViewModel()
