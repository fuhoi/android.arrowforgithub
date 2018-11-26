package com.adamlincoln.android.arrowforgithub.util

import android.content.Context
import com.adamlincoln.android.arrowforgithub.data.repo.source.RepoRepository
import com.adamlincoln.android.arrowforgithub.data.repo.source.local.RepoDatabase
import com.adamlincoln.android.arrowforgithub.data.repo.source.local.RepoLocalDataSource
import com.adamlincoln.android.arrowforgithub.data.repo.source.remote.RepoRemoteDataSource
import com.adamlincoln.android.arrowforgithub.presentation.util.AppExecutors

object Injection {

    fun provideRepoRepository(context: Context): RepoRepository {
        val database = RepoDatabase.getInstance(context)
        return RepoRepository.getInstance(
            RepoRemoteDataSource.getInstance(),
            RepoLocalDataSource.getInstance(AppExecutors(), database.repoDao())
        )
    }

}
