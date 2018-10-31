package com.lincoln.adam.githubshopifylauncher.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.lincoln.adam.githubshopifylauncher.data.RepoModel

@Dao interface RepoDao {

    @Query("SELECT * FROM Repo") fun getRepos(): List<RepoModel>

    @Query("SELECT * FROM Repo WHERE id = :repoId") fun getRepoById(repoId: String): RepoModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertRepo(repo: RepoModel)

    @Update fun updateRepo(repo: RepoModel): Int

    @Query("DELETE FROM Repo WHERE id = :repoId") fun deleteRepoById(repoId: String): Int

    @Query("DELETE FROM Repo") fun deleteRepos()

}
