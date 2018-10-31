package com.lincoln.adam.githubshopifylauncher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), MainContract.View {

    companion object {

        fun newInstance() = MainFragment()

    }

    override lateinit var presenter: MainContract.Presenter

    override var isActive: Boolean = false
        get() = isAdded

    interface RepoViewModelListener {
        fun onItemClick(viewModel: RepoViewModel)
    }

    internal var itemListener: RepoViewModelListener = object : RepoViewModelListener {
        override fun onItemClick(viewModel: RepoViewModel) {
            presenter.openTaskDetails(viewModel)
        }
    }

    private val listAdapter = RepoViewModelRecyclerViewAdapter(ArrayList(0), itemListener)

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        with(root) {
            recyclerView.adapter = listAdapter
            swipeRefreshLayout.setOnRefreshListener { presenter.loadTasks(false) }
        }

        requireActivity().findViewById<FloatingActionButton>(R.id.fab_refresh).apply {
            setOnClickListener { presenter.loadTasks(false) }
        }

        setHasOptionsMenu(true)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                presenter.loadTasks(false)
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

    override fun showTasks(tasks: List<RepoViewModel>) {
        listAdapter.values = tasks
    }

    override fun showTaskDetailsUi(htmlUrl: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(htmlUrl)))
    }
}