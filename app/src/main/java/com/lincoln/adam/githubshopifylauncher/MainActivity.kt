package com.lincoln.adam.githubshopifylauncher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val mainFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as MainFragment? ?: MainFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        mainPresenter = MainPresenter(Injection.provideTasksRepository(applicationContext), mainFragment)
    }
}
