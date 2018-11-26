package com.adamlincoln.android.arrowforgithub.presentation.splash

import com.adamlincoln.android.arrowforgithub.presentation.common.BasePresenter
import com.adamlincoln.android.arrowforgithub.presentation.common.BaseView

interface SplashContract {

    interface View : BaseView<Presenter> {

        var isActive: Boolean

        fun startTimer()
        fun startHomeActivity()

    }

    interface Presenter : BasePresenter {

        fun onTimeCompleted()

    }
}
