package com.lincoln.adam.githubshopifylauncher.presentation.repo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lincoln.adam.githubshopifylauncher.R
import kotlinx.android.synthetic.main.fragment_repo_item.view.*

class RepoAdapter(
    repoList: List<RepoViewModel>,
    private val listener: RepoFragment.RepoListener?
) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    var repoList: List<RepoViewModel> = repoList
        set(repoList) {
            field = repoList
            notifyDataSetChanged()
        }

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val viewModel = v.tag as RepoViewModel
            listener?.onRepoClick(viewModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = repoList[position]

        holder.name.text = viewModel.name
        holder.fork.text = viewModel.fork
        holder.timeSinceCreated.text = viewModel.timeSinceCreated
        holder.stargazersCount.text = viewModel.stargazersCount

        with(holder.view) {
            tag = viewModel
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int = repoList.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name:TextView = view.name
        val fork: TextView = view.fork
        val timeSinceCreated: TextView = view.timeSinceCreated
        val stargazersCount: TextView = view.stargazersCount
    }
}