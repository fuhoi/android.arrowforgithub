package com.adamlincoln.android.arrowforgithub.presentation.repo.sorting

import com.adamlincoln.android.arrowforgithub.presentation.repo.RepoViewModel

interface SortStrategy {

    fun sort(repoList: List<RepoViewModel>): List<RepoViewModel>

}