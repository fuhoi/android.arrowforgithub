package com.lincoln.adam.githubshopifylauncher.util

import android.content.Context
import com.lincoln.adam.githubshopifylauncher.data.source.RepoRepository
import com.lincoln.adam.githubshopifylauncher.data.source.remote.RepoRemoteDataSource

object Injection {

    fun provideTasksRepository(context: Context): RepoRepository {
        // val database = ToDoDatabase.getInstance(context)
        // return RepoRepository.getInstance(RepoRemoteDataSource, MainLocalDataSource.getInstance(AppExecutors(), database.taskDao()))
        return RepoRepository.getInstance(RepoRemoteDataSource.getInstance())
    }

}
