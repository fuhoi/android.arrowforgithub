package com.lincoln.adam.githubshopifylauncher

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_main_repo_view_model.view.*

class RepoViewModelRecyclerViewAdapter(
    private val mValues: List<RepoViewModel>,
    private val mListener: MainActivity.OnListFragmentInteractionListenerRepoViewModel?
) : RecyclerView.Adapter<RepoViewModelRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val viewModel = v.tag as RepoViewModel
            mListener?.onListFragmentInteraction(viewModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_main_repo_view_model, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = mValues[position]

        holder.mName.text = viewModel.name
        holder.mFork.text = viewModel.fork
        holder.mTimeSinceCreated.text = viewModel.timeSinceCreated
        holder.mStargazersCount.text = viewModel.stargazersCount

        with(holder.mView) {
            tag = viewModel
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mName:TextView = mView.name
        val mFork: TextView = mView.fork
        val mTimeSinceCreated: TextView = mView.timeSinceCreated
        val mStargazersCount: TextView = mView.stargazersCount
    }
}