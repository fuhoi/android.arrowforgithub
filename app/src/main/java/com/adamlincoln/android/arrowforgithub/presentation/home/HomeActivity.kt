package com.adamlincoln.android.arrowforgithub.presentation.home

import android.os.Bundle
import com.adamlincoln.android.arrowforgithub.R
import com.adamlincoln.android.arrowforgithub.presentation.common.BaseThemeActivity
import com.adamlincoln.android.arrowforgithub.presentation.repo.RepoFragment
import com.adamlincoln.android.arrowforgithub.presentation.repo.RepoPresenter
import com.adamlincoln.android.arrowforgithub.presentation.util.replaceFragmentInActivity
import com.adamlincoln.android.arrowforgithub.util.Injection
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseThemeActivity() {

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
