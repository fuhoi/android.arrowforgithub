package com.adamlincoln.android.arrowforgithub.presentation.repo

import com.adamlincoln.android.arrowforgithub.data.repo.RepoModel
import com.adamlincoln.android.arrowforgithub.data.repo.source.RepoDataSource
import com.adamlincoln.android.arrowforgithub.data.repo.source.RepoRepository
import com.adamlincoln.android.arrowforgithub.presentation.repo.sorting.SortNameAscCaseInsensitiveStrategy
import com.adamlincoln.android.arrowforgithub.presentation.repo.sorting.SortStrategy

class RepoPresenter(val repoRepository: RepoRepository, val repoView: RepoContract.View) : RepoContract.Presenter {

    private var firstLoad = true

    private var sortStrategy: SortStrategy = SortNameAscCaseInsensitiveStrategy()

    init {
        repoView.presenter = this
    }

    override fun start() = loadRepos(false)

    override fun onSwipeRefresh() = loadRepos(false)

    override fun onRefreshClick() = loadRepos(false)

    override fun onForceRefreshClick() = loadRepos(true)

    override fun onRetryClick() = loadRepos(true)

    private fun loadRepos(forceUpdate: Boolean) {
        loadRepos(forceUpdate || firstLoad, true)
        firstLoad = false
    }

    private fun loadRepos(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (repoView.isActive && showLoadingUI)
            repoView.showLoadingState()

        if (forceUpdate)
            repoRepository.refreshRepos()

        repoRepository.getRepos(object : RepoDataSource.RepoCallback {

            override fun onError() {
                if (repoView.isActive)
                    repoView.showErrorState()
            }

            override fun onNoData() {
                if (repoView.isActive)
                    repoView.showEmptyState()
            }

            override fun onLoaded(repoList: List<RepoModel>) {
                val repoViewModelList =
                    mapRepoModelToRepoViewModel(repoList)
                processRepos(repoViewModelList)
            }
        })
    }

    private fun processRepos(repoList: List<RepoViewModel>) {
        if (repoView.isActive) {
            val sortedRepoList = sortStrategy.sort(repoList)
            repoView.showRepoList(sortedRepoList)
        }
    }

    override fun onGitHubUrlClick(repo: RepoViewModel) {
        if (repoView.isActive)
            repoView.navigateToUrl(repo.github_url)
    }

    override fun onHomepageUrlClick(repo: RepoViewModel) {
        if (repoView.isActive)
            repoView.navigateToUrl(repo.homepage_url!!)
    }

    override fun onSort(selectedSortStrategy: SortStrategy) {
        sortStrategy = selectedSortStrategy
        loadRepos(false, false)
    }

}
