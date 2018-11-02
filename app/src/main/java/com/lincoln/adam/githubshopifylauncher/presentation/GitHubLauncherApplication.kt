package com.lincoln.adam.githubshopifylauncher.presentation

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class GitHubLauncherApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}
