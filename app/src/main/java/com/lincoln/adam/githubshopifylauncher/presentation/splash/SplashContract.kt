package com.lincoln.adam.githubshopifylauncher.presentation.splash

import com.lincoln.adam.githubshopifylauncher.presentation.BasePresenter
import com.lincoln.adam.githubshopifylauncher.presentation.BaseView

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
