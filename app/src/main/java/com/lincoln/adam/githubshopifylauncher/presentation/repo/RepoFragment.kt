package com.lincoln.adam.githubshopifylauncher.presentation.repo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.*
import android.view.animation.AnimationUtils
import com.lincoln.adam.githubshopifylauncher.R
import com.lincoln.adam.githubshopifylauncher.di.ActivityScope
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repo.*
import kotlinx.android.synthetic.main.fragment_repo.view.*
import javax.inject.Inject

@ActivityScope
class RepoFragment @Inject constructor() : DaggerFragment(), RepoContract.View {

//    @Inject
//    lateinit var presenter: RepoContract.Presenter

    override var isActive: Boolean = false
        get() = isAdded

//    interface RepoOnClickListener {
//
//        fun onGitHubUrlClick(repo: RepoViewModel)
//
//        fun onHomepageUrlClick(repo: RepoViewModel)
//
//    }
//
//    private var repoOnClickListener: RepoOnClickListener = object : RepoOnClickListener {
//
//        override fun onGitHubUrlClick(repo: RepoViewModel) {
//            presenter.onGitHubUrlClick(repo)
//        }
//
//        override fun onHomepageUrlClick(repo: RepoViewModel) {
//            presenter.onHomepageUrlClick(repo)
//        }
//
//    }

//    private val repoAdapter = RepoAdapter(ArrayList(0), repoOnClickListener)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_repo, container, false)

//        with(root) {
//            recyclerView.adapter = repoAdapter
////            val dividerItemDecoration = if (recyclerView.layoutManager is LinearLayoutManager) (recyclerView.layoutManager as LinearLayoutManager).orientation else DividerItemDecoration.VERTICAL
////            recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
////            recyclerView.setHasFixedSize(true)
//            recyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down)
//            swipeRefreshLayout.setOnRefreshListener { presenter.onSwipeRefresh() }
//        }
//
//        requireActivity().findViewById<FloatingActionButton>(R.id.fab_refresh).apply {
//            setOnClickListener { presenter.onRefreshClick() }
//        }

        setHasOptionsMenu(true)

        return root
    }

    override fun onResume() {
        super.onResume()
//        presenter.takeView(this)
//        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
//        presenter.dropView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_repo_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
//                presenter.onForceRefreshClick()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setLoadingIndicator(active: Boolean) {
        with(swipeRefreshLayout) {
            post { isRefreshing = active }
        }
    }

    override fun showRepoList(repoList: List<RepoViewModel>) {
//        repoAdapter.repoList = repoList
        recyclerView.scheduleLayoutAnimation()  // Forces items to always animate.
    }

    override fun navigateToUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}