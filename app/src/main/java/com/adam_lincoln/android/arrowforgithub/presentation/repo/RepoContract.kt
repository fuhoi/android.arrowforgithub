package com.adam_lincoln.android.arrowforgithub.presentation.repo

import com.adam_lincoln.android.arrowforgithub.presentation.BasePresenter
import com.adam_lincoln.android.arrowforgithub.presentation.BaseView

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

    }
}