package com.adam_lincoln.android.arrowforgithub.presentation.theme

import android.content.Context
import android.net.sip.SipSession
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatRadioButton
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adam_lincoln.android.arrowforgithub.R
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

        holder.radioButtonColorName.text = context.getString(viewModel.nameResId)
        holder.imageViewColorPrimary.setImageResource(viewModel.colorPrimaryResId)
        holder.imageViewColorPrimaryDark.setImageResource(viewModel.colorPrimaryDarkResId)
        holder.imageViewColorAccent.setImageResource(viewModel.colorAccentColorResId)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val radioButtonColorName: AppCompatRadioButton = view.radioButtonColorName
        val imageViewColorPrimary: AppCompatImageView = view.imageViewColorPrimary
        val imageViewColorPrimaryDark: AppCompatImageView = view.imageViewColorPrimaryDark
        val imageViewColorAccent: AppCompatImageView = view.imageViewColorAccent

        init {
            view.setOnClickListener { onThemeSelectedListener.onThemeSelectedListener(view.tag as ThemeViewModel) }
        }
    }

    interface OnThemeSelectedListener {
        fun onThemeSelectedListener(theme: ThemeViewModel)
    }

}
