package com.lincoln.adam.githubshopifylauncher.presentation.app

import com.lincoln.adam.githubshopifylauncher.di.ActivityScope
import com.lincoln.adam.githubshopifylauncher.presentation.home.HomeActivity
import com.lincoln.adam.githubshopifylauncher.presentation.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun homeActivity(): HomeActivity

}
