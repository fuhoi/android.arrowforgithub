package com.adamlincoln.android.arrowforgithub.presentation.repo.sorting

import com.adamlincoln.android.arrowforgithub.presentation.repo.RepoViewModel

class SortNameDescCaseInsensitiveStrategy : SortStrategy {

    override fun sort(repoList: List<RepoViewModel>): List<RepoViewModel> =
        repoList.sortedWith(compareByDescending { it.name.toLowerCase() })

}
