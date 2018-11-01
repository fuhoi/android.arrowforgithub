package com.lincoln.adam.githubshopifylauncher.repo

import com.lincoln.adam.githubshopifylauncher.data.RepoModel
import com.lincoln.adam.githubshopifylauncher.data.source.RepoDataSource
import com.lincoln.adam.githubshopifylauncher.data.source.RepoRepository
import com.lincoln.adam.githubshopifylauncher.util.mapRepoModelToRepoViewModel

class RepoPresenter(val repoRepository: RepoRepository, val repoView: RepoContract.View) : RepoContract.Presenter {

    private var firstLoad = true

    init {
        repoView.presenter = this
    }

    override fun start() {
        loadRepos(false)
    }

    override fun loadRepos(forceUpdate: Boolean) {
        loadRepos(forceUpdate || firstLoad, true)
        firstLoad = false
    }

    private fun loadRepos(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (showLoadingUI)
            repoView.setLoadingIndicator(true)

        if (forceUpdate)
            repoRepository.refreshRepos()

        repoRepository.getRepos(object : RepoDataSource.RepoCallback {
            override fun onLoaded(repoList: List<RepoModel>) {
                // The view may not be able to handle UI updates anymore.
                if (!repoView.isActive)
                    return

                if (showLoadingUI)
                    repoView.setLoadingIndicator(false)

                val repoViewModelList = mapRepoModelToRepoViewModel(repoList)
                processRepos(repoViewModelList)
            }

            override fun onDataNotAvailable() {
                TODO("not implemented")
            }
        })
    }

    private fun processRepos(repoList: List<RepoViewModel>) {
        repoView.showRepoList(repoList)
    }

    override fun onRepoClick(repo: RepoViewModel) {
        repoView.navigateToUrl(repo.htmlUrl)
    }
}