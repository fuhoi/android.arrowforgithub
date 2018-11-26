package com.adamlincoln.android.arrowforgithub.presentation.repo.sorting

import com.adamlincoln.android.arrowforgithub.presentation.repo.RepoViewModel

class SortForkedCountStrategy : SortStrategy {

    override fun sort(repoList: List<RepoViewModel>): List<RepoViewModel> =
        repoList.sortedWith(compareByDescending { it.fork_count })

}
