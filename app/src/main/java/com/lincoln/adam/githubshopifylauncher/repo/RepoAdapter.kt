package com.lincoln.adam.githubshopifylauncher.repo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lincoln.adam.githubshopifylauncher.R
import kotlinx.android.synthetic.main.fragment_repo_item.view.*
import android.widget.Button

class RepoAdapter(
    repoList: List<RepoViewModel>,
    private val listener: RepoFragment.RepoOnClickListener?
) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    var repoList: List<RepoViewModel> = repoList
        set(repoList) {
            field = repoList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_repo_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = repoList[position]
        holder.view.tag = viewModel

        holder.name.text = viewModel.name
        holder.description.text = viewModel.description
        holder.language.text = viewModel.language
        holder.stargazersCount.text = viewModel.stargazers_count
        holder.forkText.text = viewModel.fork_text
        holder.timeSinceCreated.text = viewModel.time_since_created
        holder.githubUrl.isEnabled = viewModel.github_url.isNotBlank()
        holder.homepageUrl.isEnabled = viewModel.homepage_url?.isNotBlank() ?: false
    }

    override fun getItemCount(): Int = repoList.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.name
        val description: TextView = view.description
        val language: TextView = view.language
        val stargazersCount: TextView = view.stargazers_count
        val forkText: TextView = view.fork_text
        val timeSinceCreated: TextView = view.time_since_created
        val githubUrl: Button = view.github_url
        val homepageUrl: Button = view.homepage_url

        init {
            githubUrl.setOnClickListener { listener?.onGitHubUrlClick(view.tag as RepoViewModel) }
            homepageUrl.setOnClickListener { listener?.onHomepageUrlClick(view.tag as RepoViewModel) }
        }
    }
}