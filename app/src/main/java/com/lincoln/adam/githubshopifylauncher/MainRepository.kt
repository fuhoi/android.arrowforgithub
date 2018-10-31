package com.lincoln.adam.githubshopifylauncher

class MainRepository(
    val mainRemoteDataSource: MainDataSource
    //,val mainLocalDataSource: MainDataSource?
) : MainDataSource {

    companion object {

        private var INSTANCE: MainRepository? = null

        @JvmStatic fun getInstance(mainRemoteDataSource: MainDataSource /*, mainLocalDataSource: MainDataSource*/): MainRepository {
            return INSTANCE ?: MainRepository(mainRemoteDataSource /*, mainLocalDataSource*/).apply { INSTANCE = this }
        }
    }

    override fun getTasks(callback: MainDataSource.LoadTasksCallback) {
        getTasksFromRemoteDataSource(callback)
    }

    private fun getTasksFromRemoteDataSource(callback: MainDataSource.LoadTasksCallback) {
        mainRemoteDataSource.getTasks(object : MainDataSource.LoadTasksCallback {
            override fun onTasksLoaded(models: List<RepoModel>) {
                callback.onTasksLoaded(ArrayList(models))
            }
        })
    }

    override fun refreshTasks() {
        // cacheIsDirty = true
    }
}