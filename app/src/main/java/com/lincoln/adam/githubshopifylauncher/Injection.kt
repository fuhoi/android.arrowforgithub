package com.lincoln.adam.githubshopifylauncher

import android.content.Context

object Injection {

    fun provideTasksRepository(context: Context): MainRepository {
        // val database = ToDoDatabase.getInstance(context)
        // return MainRepository.getInstance(MainRemoteDataSource, MainLocalDataSource.getInstance(AppExecutors(), database.taskDao()))
        return MainRepository.getInstance(MainRemoteDataSource)
    }

}
