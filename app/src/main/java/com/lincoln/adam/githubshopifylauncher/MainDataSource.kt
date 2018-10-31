package com.lincoln.adam.githubshopifylauncher


interface MainDataSource {

    interface LoadTasksCallback {

        fun onTasksLoaded(models: List<RepoModel>)

//        fun onDataNotAvailable()

    }

    interface GetTaskCallback {

        fun onTaskLoaded(model: RepoModel)

//        fun onDataNotAvailable()

    }

    fun getTasks(callback: LoadTasksCallback)

    fun refreshTasks()

}