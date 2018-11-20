package com.adam_lincoln.android.arrowforgithub.presentation.home

import android.os.Bundle
import com.adam_lincoln.android.arrowforgithub.R
import com.adam_lincoln.android.arrowforgithub.presentation.BaseActivity
import com.adam_lincoln.android.arrowforgithub.presentation.BaseThemeActivity
import com.adam_lincoln.android.arrowforgithub.presentation.repo.RepoFragment
import com.adam_lincoln.android.arrowforgithub.presentation.repo.RepoPresenter
import com.adam_lincoln.android.arrowforgithub.presentation.util.replaceFragmentInActivity
import com.adam_lincoln.android.arrowforgithub.util.Injection
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
