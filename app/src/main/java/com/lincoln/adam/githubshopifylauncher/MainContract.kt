package com.lincoln.adam.githubshopifylauncher

interface MainContract {

    interface View : BaseView<Presenter> {

        var isActive: Boolean

        fun setLoadingIndicator(active: Boolean)

        fun showTasks(tasks: List<RepoViewModel>)

        fun showTaskDetailsUi(htmlUrl: String)

//        fun showLoadingTasksError()

//        fun showNoTasks()

    }

    interface Presenter : BasePresenter {

        fun loadTasks(forceUpdate: Boolean)

        fun openTaskDetails(requestedTask: RepoViewModel)

    }
}