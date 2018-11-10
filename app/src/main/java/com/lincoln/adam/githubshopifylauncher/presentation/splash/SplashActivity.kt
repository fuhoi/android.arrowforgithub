package com.lincoln.adam.githubshopifylauncher.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.lincoln.adam.githubshopifylauncher.R
import com.lincoln.adam.githubshopifylauncher.presentation.BaseActivity
import com.lincoln.adam.githubshopifylauncher.presentation.home.HomeActivity

class SplashActivity : BaseActivity(), SplashContract.View {

    override lateinit var presenter: SplashContract.Presenter

    override var isActive: Boolean = false
        get() = isActivityResumed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun startTimer() {
        runOnUiThread { Handler().postDelayed({ presenter.onTimeCompleted() }, 3000) }
    }

    override fun startHomeActivity() {
        startActivity(Intent(baseContext, HomeActivity::class.java))
    }

}
