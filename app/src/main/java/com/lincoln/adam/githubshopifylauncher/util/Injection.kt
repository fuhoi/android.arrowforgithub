package com.lincoln.adam.githubshopifylauncher.util

import android.content.Context
import com.lincoln.adam.githubshopifylauncher.data.source.RepoRepository
import com.lincoln.adam.githubshopifylauncher.data.source.local.RepoDatabase
import com.lincoln.adam.githubshopifylauncher.data.source.local.RepoLocalDataSource
import com.lincoln.adam.githubshopifylauncher.data.source.remote.RepoRemoteDataSource
import com.lincoln.adam.githubshopifylauncher.presentation.util.AppExecutors

object Injection {

    fun provideRepoRepository(context: Context): RepoRepository {
        val database = RepoDatabase.getInstance(context)
        return RepoRepository.getInstance(
            RepoRemoteDataSource.getInstance(),
            RepoLocalDataSource.getInstance(AppExecutors(), database.repoDao())
        )
    }

}
