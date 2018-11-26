package com.adamlincoln.android.arrowforgithub.presentation.repo.sorting

import android.arch.lifecycle.ViewModel

data class SortStrategyViewModel(
    val id: Int,
    val name: String,
    val sortStrategy: SortStrategy
) : ViewModel()
