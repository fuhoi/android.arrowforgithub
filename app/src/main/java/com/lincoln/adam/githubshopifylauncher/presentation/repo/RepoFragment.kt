package com.lincoln.adam.githubshopifylauncher.presentation.repo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.*
import com.lincoln.adam.githubshopifylauncher.R
import kotlinx.android.synthetic.main.fragment_repo.*
import kotlinx.android.synthetic.main.fragment_repo.view.*

class RepoFragment : Fragment(), RepoContract.View {

    companion object {

        fun newInstance() = RepoFragment()

    }

    override lateinit var presenter: RepoContract.Presenter

    override var isActive: Boolean = false
        get() = isAdded

    interface RepoListener {

        fun onRepoClick(repo: RepoViewModel)

    }

    private var repoListener: RepoListener = object: RepoListener {
        override fun onRepoClick(repo: RepoViewModel) {
            presenter.onRepoClick(repo)
        }
    }

    private val repoAdapter = RepoAdapter(ArrayList(0), repoListener)

    override fun onResume() {
        super.onResume()

        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_repo, container, false)

        with(root) {
            recyclerView.adapter = repoAdapter
            recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
            swipeRefreshLayout.setOnRefreshListener { presenter.loadRepos(false) }
        }

        requireActivity().findViewById<FloatingActionButton>(R.id.fab_refresh).apply {
            setOnClickListener { presenter.loadRepos(false) }
        }

        setHasOptionsMenu(true)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_repo_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                presenter.loadRepos(true)
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
        repoAdapter.repoList = repoList
    }

    override fun navigateToUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}