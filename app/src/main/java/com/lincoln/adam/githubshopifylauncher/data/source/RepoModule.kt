package com.lincoln.adam.githubshopifylauncher.data.source

//@Module
//abstract class RepoModule {
//
//    companion object {
//        private const val THREAD_COUNT = 3
//    }
//
//    @Singleton
//    @Binds
//    @Local
//    abstract fun provideRepoLocalDataSource(dataSource: RepoLocalDataSource): RepoDataSource
//
//    @Singleton
//    @Binds
//    @Remote
//    abstract fun provideRepoRemoteDataSource(dataSource: RepoRemoteDataSource): RepoDataSource
//
////    @Singleton
////    @Provides
////    fun provideRepoDatabase(context: Application): RepoDatabase = RepoDatabase.getInstance(context)
//
////    @Singleton
////    @Provides
////    fun provideRepoDao(database: RepoDatabase): RepoDao = database.repoDao()
//
////    @Singleton
////    @Provides
////    fun provideAppExecutors(): AppExecutors {
////        return AppExecutors(
////            DiskIOThreadExecutor(),
////            Executors.newFixedThreadPool(THREAD_COUNT),
////            AppExecutors.MainThreadExecutor()
////        )
////    }
//
//}
