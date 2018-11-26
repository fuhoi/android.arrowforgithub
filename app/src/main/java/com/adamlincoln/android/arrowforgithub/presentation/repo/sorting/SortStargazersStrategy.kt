package com.adamlincoln.android.arrowforgithub.presentation.repo.sorting

import com.adamlincoln.android.arrowforgithub.presentation.repo.RepoViewModel

class SortStargazersStrategy : SortStrategy {

    override fun sort(repoList: List<RepoViewModel>): List<RepoViewModel> =
        repoList.sortedWith(compareByDescending { it.stargazers_count })

}
