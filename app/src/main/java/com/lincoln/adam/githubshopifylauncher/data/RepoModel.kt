package com.lincoln.adam.githubshopifylauncher.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class RepoModel(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String?,
    val fork: Boolean,
    val created_at: String,
    val stargazers_count: Int,
    val html_url: String,
    val homepage: String?,
    val language: String?,
    val archived: Boolean
)
