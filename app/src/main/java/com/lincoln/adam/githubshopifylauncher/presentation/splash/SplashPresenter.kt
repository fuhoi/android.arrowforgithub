package com.lincoln.adam.githubshopifylauncher.presentation.splash

class SplashPresenter(val view: SplashContract.View) : SplashContract.Presenter {

    override fun start() {
        view.startTimer()
    }

    override fun onTimeCompleted() {
        view.startHomeActivity()
    }

}