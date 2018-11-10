package com.lincoln.adam.githubshopifylauncher.presentation.home

import android.os.Bundle
import com.lincoln.adam.githubshopifylauncher.R
import com.lincoln.adam.githubshopifylauncher.presentation.BaseActivity
import com.lincoln.adam.githubshopifylauncher.presentation.repo.RepoFragment
import com.lincoln.adam.githubshopifylauncher.presentation.repo.RepoPresenter
import com.lincoln.adam.githubshopifylauncher.util.Injection
import com.lincoln.adam.githubshopifylauncher.presentation.util.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private lateinit var repoPresenter: RepoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val repoFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as RepoFragment?
            ?: RepoFragment.newInstance().also {
                replaceFragmentInActivity(it, R.id.contentFrame)
            }

        repoPresenter = RepoPresenter(Injection.provideRepoRepository(applicationContext), repoFragment)
    }
}
