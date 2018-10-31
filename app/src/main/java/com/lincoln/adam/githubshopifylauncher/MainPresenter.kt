package com.lincoln.adam.githubshopifylauncher

class MainPresenter(val mainRepository: MainRepository, val mainView: MainContract.View) : MainContract.Presenter {

    private var firstLoad = true

    init {
        mainView.presenter = this
    }

    override fun start() {
        loadTasks(false)
    }

    override fun loadTasks(forceUpdate: Boolean) {
        loadTasks(forceUpdate || firstLoad, true)
        firstLoad = false
    }

    private fun loadTasks(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (showLoadingUI)
            mainView.setLoadingIndicator(true)

        if (forceUpdate)
            mainRepository.refreshTasks()

        mainRepository.getTasks(object : MainDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<RepoModel>) {
                val viewModelList = mapModelToViewModel(tasks)

                // The view may not be able to handle UI updates anymore
                if (!mainView.isActive)
                    return

                if (showLoadingUI)
                    mainView.setLoadingIndicator(false)

                processTasks(viewModelList)
            }
        })
    }

    private fun processTasks(tasks: List<RepoViewModel>) {
        mainView.showTasks(tasks)
    }

    override fun openTaskDetails(requestedTask: RepoViewModel) {
        mainView.showTaskDetailsUi(requestedTask.htmlUrl)
    }
}