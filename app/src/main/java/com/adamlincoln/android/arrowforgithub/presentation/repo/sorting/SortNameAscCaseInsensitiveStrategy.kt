package com.adamlincoln.android.arrowforgithub.presentation.repo.sorting

import com.adamlincoln.android.arrowforgithub.presentation.repo.RepoViewModel

class SortNameAscCaseInsensitiveStrategy : SortStrategy {

    override fun sort(repoList: List<RepoViewModel>): List<RepoViewModel> =
        repoList.sortedWith(compareBy { it.name.toLowerCase() })

}
