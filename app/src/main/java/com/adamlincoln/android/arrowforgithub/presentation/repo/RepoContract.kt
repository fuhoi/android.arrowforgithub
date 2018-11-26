package com.adamlincoln.android.arrowforgithub.presentation.repo

import com.adamlincoln.android.arrowforgithub.presentation.common.BasePresenter
import com.adamlincoln.android.arrowforgithub.presentation.common.BaseView
import com.adamlincoln.android.arrowforgithub.presentation.repo.sorting.SortStrategy

interface RepoContract {

    interface View : BaseView<Presenter> {

        var isActive: Boolean

        fun showEmptyState()

        fun showLoadingState()

        fun showErrorState()

        fun showRepoList(repoList: List<RepoViewModel>)

        fun navigateToUrl(url: String)

    }

    interface Presenter : BasePresenter {

        fun onSwipeRefresh()

        fun onRefreshClick()

        fun onForceRefreshClick()

        fun onRetryClick()

        fun onGitHubUrlClick(repo: RepoViewModel)

        fun onHomepageUrlClick(repo: RepoViewModel)

        fun onSort(selectedSortStrategy: SortStrategy)

    }
}