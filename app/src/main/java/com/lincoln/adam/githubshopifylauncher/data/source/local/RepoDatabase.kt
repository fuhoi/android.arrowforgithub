package com.lincoln.adam.githubshopifylauncher.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.lincoln.adam.githubshopifylauncher.data.RepoModel

@Database(entities = [RepoModel::class], version = 1, exportSchema = true)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao

    companion object {

        private var INSTANCE: RepoDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): RepoDatabase {
            if (INSTANCE == null) {
                synchronized(RepoDatabase::javaClass) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RepoDatabase::class.java,
                            "Repo.db"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

}