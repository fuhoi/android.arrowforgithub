package com.lincoln.adam.githubshopifylauncher.presentation.repo

import com.lincoln.adam.githubshopifylauncher.di.ActivityScope
import com.lincoln.adam.githubshopifylauncher.presentation.util.mapRepoModelToRepoViewModel

@ActivityScope
class RepoPresenter : RepoContract.Presenter {

//    @Inject
//    lateinit var repoRepository: RepoRepository

    var repoView: RepoContract.View? = null

    private var firstLoad = true

//    override fun start() = loadRepos(false)

    override fun takeView(view: RepoContract.View) {
        repoView = view
        loadRepos(false)
    }

    override fun dropView() {
        repoView = null
    }

    override fun onSwipeRefresh() = loadRepos(false)

    override fun onRefreshClick() = loadRepos(false)

    override fun onForceRefreshClick() = loadRepos(true)

    private fun loadRepos(forceUpdate: Boolean) {
        loadRepos(forceUpdate || firstLoad, true)
        firstLoad = false
    }

    private fun loadRepos(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (showLoadingUI)
            repoView?.setLoadingIndicator(true)

//        if (forceUpdate)
//            repoRepository.refreshRepos()

//        repoRepository.getRepos(object : RepoDataSource.RepoCallback {
//            override fun onLoaded(repoList: List<RepoModel>) {
//                // The view may not be able to handle UI updates anymore.
//                if (repoView?.isActive == false)
//                    return
//
//                if (showLoadingUI)
//                    repoView?.setLoadingIndicator(false)
//
//                val repoViewModelList = mapRepoModelToRepoViewModel(repoList)
//                processRepos(repoViewModelList)
//            }
//
//            override fun onDataNotAvailable() {
//                TODO("not implemented")
//            }
//        })

        if (repoView?.isActive == false)
            return

        if (showLoadingUI)
            repoView?.setLoadingIndicator(false)

        val repoViewModelList = mapRepoModelToRepoViewModel(ArrayList(0))
        processRepos(repoViewModelList)
    }

    private fun processRepos(repoList: List<RepoViewModel>) {
        repoView?.showRepoList(repoList)
    }

    override fun onGitHubUrlClick(repo: RepoViewModel) {
        repoView?.navigateToUrl(repo.github_url)
    }

    override fun onHomepageUrlClick(repo: RepoViewModel) {
        repoView?.navigateToUrl(repo.homepage_url!!)
    }
}