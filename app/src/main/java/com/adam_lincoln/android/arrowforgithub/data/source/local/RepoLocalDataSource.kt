package com.adam_lincoln.android.arrowforgithub.data.source.local

import com.adam_lincoln.android.arrowforgithub.data.RepoModel
import com.adam_lincoln.android.arrowforgithub.data.source.RepoDataSource
import com.adam_lincoln.android.arrowforgithub.presentation.util.AppExecutors

class RepoLocalDataSource private constructor(
    private val appExecutors: AppExecutors,
    private val repoDao: RepoDao
) : RepoDataSource {

    companion object {

        private var INSTANCE: RepoLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, repoDao: RepoDao): RepoLocalDataSource {
            if (INSTANCE == null) {
                synchronized(RepoLocalDataSource::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = RepoLocalDataSource(appExecutors, repoDao)
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun getRepos(repoCallback: RepoDataSource.RepoCallback) {
        appExecutors.diskIO.execute {
            val repoList = repoDao.getRepos()
            appExecutors.mainThread.execute {
                if (repoList.isEmpty()) {
                    repoCallback.onDataNotAvailable()  // This will be called if the table is new or just empty.
                } else {
                    repoCallback.onLoaded(repoList)
                }
            }
        }
    }

    override fun saveRepo(repo: RepoModel) {
        appExecutors.diskIO.execute { repoDao.insertRepo(repo) }
    }

    override fun refreshRepos() {
        // N/A - Implemented by RepoRepository.
    }

    override fun deleteAllRepos() {
        appExecutors.diskIO.execute { repoDao.deleteRepos() }
    }
}