package com.adamlincoln.android.arrowforgithub.presentation.theme

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamlincoln.android.arrowforgithub.R
import kotlinx.android.synthetic.main.dialog_theme_item.view.*

class ThemeAdapter(
    val context: Context,
    list: List<ThemeViewModel>,
    val onThemeSelectedListener: OnThemeSelectedListener
) : RecyclerView.Adapter<ThemeAdapter.ViewHolder>() {

    var list: List<ThemeViewModel> = list
        set(list) {
            field = list
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dialog_theme_item, parent, false))

    override fun onBindViewHolder(holder: ThemeAdapter.ViewHolder, position: Int) {
        val viewModel = list[position]
        holder.view.tag = viewModel

        holder.textViewColourName.text = context.getString(viewModel.colourNameResId)
        holder.imageViewColourPrimary.setImageResource(viewModel.colourPrimaryResId)
        holder.imageViewColourPrimaryDark.setImageResource(viewModel.colourPrimaryDarkResId)
        holder.imageViewColourAccent.setImageResource(viewModel.colourAccentColorResId)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textViewColourName: AppCompatTextView = view.textViewColourName
        val imageViewColourPrimary: AppCompatImageView = view.imageViewColourPrimary
        val imageViewColourPrimaryDark: AppCompatImageView = view.imageViewColourPrimaryDark
        val imageViewColourAccent: AppCompatImageView = view.imageViewColourAccent

        init {
            view.setOnClickListener { onThemeSelectedListener.onThemeSelectedListener(view.tag as ThemeViewModel) }
        }
    }

    interface OnThemeSelectedListener {
        fun onThemeSelectedListener(theme: ThemeViewModel)
    }

}
