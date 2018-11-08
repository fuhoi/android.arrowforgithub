package com.lincoln.adam.githubshopifylauncher.presentation.home

import com.lincoln.adam.githubshopifylauncher.di.FragmentScope
import com.lincoln.adam.githubshopifylauncher.presentation.repo.RepoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun repoFragment(): RepoFragment

}
