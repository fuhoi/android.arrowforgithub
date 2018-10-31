package com.lincoln.adam.githubshopifylauncher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v7.widget.DividerItemDecoration
import android.content.Intent
import android.net.Uri
import android.os.Looper
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    companion object {
        const val GITHUB_BASE_URL =  "https://api.github.com/"
        const val GITHUB_ORG_NAME = "shopify"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { refresh() }
        swipeRefresh.setOnRefreshListener { refresh() }
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_refresh -> {
                refresh()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        refresh()
    }

    private var inProgress: Boolean = false

    private fun refresh() {
        if (!Thread.currentThread().equals(Looper.getMainLooper().getThread()))
            throw RuntimeException("refresh must be called on the UI thread")

        if (inProgress) {
            swipeRefresh.isRefreshing = false
            return
        }

        inProgress = true
        swipeRefresh.isRefreshing = true

        val retrofit = Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)

        val name = GITHUB_ORG_NAME
        val call = service.listRepos(name)
        call.enqueue(object : Callback<List<RepoModel>> {
            override fun onResponse(call: Call<List<RepoModel>>, response: Response<List<RepoModel>>) {
                inProgress = false
                swipeRefresh.isRefreshing = false
                val modelList = response.body()!!
                val viewModelList = mapModelToViewModel(modelList)
                recyclerView.adapter = RepoViewModelRecyclerViewAdapter(viewModelList, object : OnListFragmentInteractionListenerRepoViewModel {
                    override fun onListFragmentInteraction(viewModel: RepoViewModel) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.htmlUrl)))
                    }
                })
            }

            override fun onFailure(call: Call<List<RepoModel>>, t: Throwable) {
                inProgress = false
                swipeRefresh.isRefreshing = false
                Snackbar.make(content, "Error: $t.message", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                t.printStackTrace()
            }
        })
    }

    interface OnListFragmentInteractionListenerRepoViewModel {
        fun onListFragmentInteraction(viewModel: RepoViewModel)
    }
}
