package com.adam_lincoln.android.arrowforgithub.presentation.repo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.adam_lincoln.android.arrowforgithub.R
import kotlinx.android.synthetic.main.fragment_repo_item.view.*

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

        setTextAndVisibility(holder.name, viewModel.name)
        setTextAndVisibility(holder.description, viewModel.description)
        setTextAndVisibility(holder.language, viewModel.language)
        setTextAndVisibility(holder.stargazersCount, viewModel.stargazers_count)
        setTextAndVisibility(holder.forkText, viewModel.fork_text)
        setTextAndVisibility(holder.timeSinceCreated, viewModel.time_since_created)
        setTextAndVisibility(holder.forkCount, viewModel.fork_count)

        setEnabled(holder.githubUrl, viewModel.github_url)
        setEnabled(holder.homepageUrl, viewModel.homepage_url)
    }

    private fun setTextAndVisibility(textView: TextView, text: String?) {
        textView.text = text
        textView.visibility = if (text?.isNotEmpty() == true) View.VISIBLE else View.GONE
    }

    private fun setEnabled(v: View, text: String?) {
        v.isEnabled = text?.isNotEmpty() ?: false
    }

    override fun getItemCount(): Int = repoList.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.name
        val description: TextView = view.description
        val language: TextView = view.language
        val stargazersCount: TextView = view.stargazers_count
        val forkText: TextView = view.fork_text
        val timeSinceCreated: TextView = view.time_since_created
        val forkCount: TextView = view.fork_count
        val githubUrl: Button = view.github_url
        val homepageUrl: Button = view.homepage_url

        init {
            githubUrl.setOnClickListener { listener?.onGitHubUrlClick(view.tag as RepoViewModel) }
            homepageUrl.setOnClickListener { listener?.onHomepageUrlClick(view.tag as RepoViewModel) }
        }
    }
}