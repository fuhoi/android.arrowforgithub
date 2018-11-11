package com.adam_lincoln.android.arrowforgithub.util

import android.content.Context
import com.adam_lincoln.android.arrowforgithub.data.source.RepoRepository
import com.adam_lincoln.android.arrowforgithub.data.source.local.RepoDatabase
import com.adam_lincoln.android.arrowforgithub.data.source.local.RepoLocalDataSource
import com.adam_lincoln.android.arrowforgithub.data.source.remote.RepoRemoteDataSource
import com.adam_lincoln.android.arrowforgithub.presentation.util.AppExecutors

object Injection {

    fun provideRepoRepository(context: Context): RepoRepository {
        val database = RepoDatabase.getInstance(context)
        return RepoRepository.getInstance(
            RepoRemoteDataSource.getInstance(),
            RepoLocalDataSource.getInstance(AppExecutors(), database.repoDao())
        )
    }

}
