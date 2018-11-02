package com.lincoln.adam.githubshopifylauncher.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lincoln.adam.githubshopifylauncher.R
import com.lincoln.adam.githubshopifylauncher.presentation.repo.RepoFragment
import com.lincoln.adam.githubshopifylauncher.presentation.repo.RepoPresenter
import com.lincoln.adam.githubshopifylauncher.presentation.util.Injection
import com.lincoln.adam.githubshopifylauncher.presentation.util.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var repoPresenter: RepoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val repoFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as RepoFragment? ?: RepoFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        repoPresenter = RepoPresenter(Injection.provideTasksRepository(applicationContext), repoFragment)
    }
}
