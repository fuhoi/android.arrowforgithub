package com.lincoln.adam.githubshopifylauncher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lincoln.adam.githubshopifylauncher.repo.RepoFragment
import com.lincoln.adam.githubshopifylauncher.repo.RepoPresenter
import com.lincoln.adam.githubshopifylauncher.util.Injection
import com.lincoln.adam.githubshopifylauncher.util.replaceFragmentInActivity
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
