package com.lincoln.adam.githubshopifylauncher

data class RepoModel(val id: Int, val name: String, val fork: Boolean, val created_at: String, val stargazers_count: Int, val html_url: String)
