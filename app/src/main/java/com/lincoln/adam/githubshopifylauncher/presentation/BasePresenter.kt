package com.lincoln.adam.githubshopifylauncher.presentation

interface BasePresenter<T> {

//    fun start()

    fun takeView(view: T)

    fun dropView()

}
