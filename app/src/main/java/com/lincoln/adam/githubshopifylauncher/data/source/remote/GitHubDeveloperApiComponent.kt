package com.lincoln.adam.githubshopifylauncher.data.source.remote

import dagger.Component

@Component(modules = [GitHubDeveloperApiModule::class])
interface GitHubDeveloperApiComponent {
    fun inject(repoRemoteDataSource: RepoRemoteDataSource)
}
