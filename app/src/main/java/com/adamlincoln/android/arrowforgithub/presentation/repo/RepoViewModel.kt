package com.adamlincoln.android.arrowforgithub.presentation.repo

import android.arch.lifecycle.ViewModel
import org.joda.time.DateTime

data class RepoViewModel(
    val id: String,
    val name: String,
    val description: String?,
    val fork: Boolean,
    val fork_text: String,
    val fork_count: Int,
    val time_since_created_text: String,
    val time_since_created_date: DateTime,
    val stargazers_text: String,
    val stargazers_count: Int,
    val github_url: String,
    val homepage_url: String?,
    val language: String?,
    val archived: Boolean,
    val archived_text: String
) : ViewModel()
