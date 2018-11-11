package com.adam_lincoln.android.arrowforgithub.presentation.splash

import com.adam_lincoln.android.arrowforgithub.presentation.BasePresenter
import com.adam_lincoln.android.arrowforgithub.presentation.BaseView

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
