package com.adamlincoln.android.arrowforgithub.presentation.repo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.adamlincoln.android.arrowforgithub.R
import com.adamlincoln.android.arrowforgithub.presentation.repo.sorting.*
import kotlinx.android.synthetic.main.fragment_repo.*
import kotlinx.android.synthetic.main.fragment_repo.view.*
import timber.log.Timber

class RepoFragment : Fragment(), RepoContract.View {

    companion object {

        fun newInstance() = RepoFragment()

    }

    override lateinit var presenter: RepoContract.Presenter

    override var isActive: Boolean = false
        get() = isAdded

    private val repoOnClickListener: RepoOnClickListener = object :
        RepoOnClickListener {

        override fun onGitHubUrlClick(repo: RepoViewModel) {
            presenter.onGitHubUrlClick(repo)
        }

        override fun onHomepageUrlClick(repo: RepoViewModel) {
            presenter.onHomepageUrlClick(repo)
        }

    }

    private lateinit var repoAdapter: RepoAdapter

    private val sortStrategyList = listOf("Name A-Z", "Name Z-A", "Forked Count", "Stargazers", "Id")

    private var defaultSortStrategy: SortStrategy = SortNameAscCaseInsensitiveStrategy()

    private var selectedSortStrategy: SortStrategy = defaultSortStrategy

    private val sortOnItemSelectedListener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val strategy = sortStrategyList[position]
                Timber.d("position: $position, strategy: $strategy, id: $id")

                selectedSortStrategy = if (strategy == "Name A-Z")
                    SortNameAscCaseInsensitiveStrategy()
                else if (strategy == "Name Z-A")
                    SortNameDescCaseInsensitiveStrategy()
                else if (strategy == "Forked Count")
                    SortForkedCountStrategy()
                else if (strategy == "Stargazers")
                    SortStargazersStrategy()
                else if (strategy == "Id")
                    SortIdStrategy()
                else
                    defaultSortStrategy

                presenter.onSort(selectedSortStrategy)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedSortStrategy = defaultSortStrategy
            }

        }

    private lateinit var sortAdapter: ArrayAdapter<String>

    override fun onResume() {
        super.onResume()

        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_repo, container, false)

        with(root) {
            swipeRefreshLayout.setOnRefreshListener { presenter.onSwipeRefresh() }

            sortAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, sortStrategyList)
            sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sortSpinner.adapter = sortAdapter
            sortSpinner.onItemSelectedListener = sortOnItemSelectedListener

            repoAdapter = RepoAdapter(context, ArrayList(0), repoOnClickListener)
            recyclerView.adapter = repoAdapter
//            val dividerItemDecoration = if (recyclerView.layoutManager is LinearLayoutManager) (recyclerView.layoutManager as LinearLayoutManager).orientation else DividerItemDecoration.VERTICAL
//            recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
//            recyclerView.setHasFixedSize(true)
            recyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down)
        }

        requireActivity().findViewById<FloatingActionButton>(R.id.fab_refresh).apply {
            setOnClickListener { presenter.onRefreshClick() }
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
                presenter.onForceRefreshClick()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showEmptyState() {
        emptyState.visibility = View.VISIBLE
        errorState.visibility = View.GONE
        loadingState.visibility = View.GONE
        loadedState.visibility = View.GONE
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = false }
    }

    override fun showLoadingState() {
        emptyState.visibility = View.GONE
        errorState.visibility = View.GONE
        loadingState.visibility = View.VISIBLE
        loadedState.visibility = View.GONE
        swipeRefreshLayout?.post { swipeRefreshLayout?.isRefreshing = true }
    }

    override fun showErrorState() {
        emptyState.visibility = View.GONE
        errorState.visibility = View.VISIBLE
        loadingState.visibility = View.GONE
        loadedState.visibility = View.GONE
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = false }
    }

    override fun showRepoList(repoList: List<RepoViewModel>) {
        emptyState.visibility = View.GONE
        errorState.visibility = View.GONE
        loadingState.visibility = View.GONE
        loadedState.visibility = View.VISIBLE
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = false }

        repoAdapter.repoList = repoList
        recyclerView.scheduleLayoutAnimation()  // Forces items to always animate.
    }

    override fun navigateToUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}