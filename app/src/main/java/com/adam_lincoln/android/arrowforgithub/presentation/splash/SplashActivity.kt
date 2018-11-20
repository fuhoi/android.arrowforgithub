package com.adam_lincoln.android.arrowforgithub.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.adam_lincoln.android.arrowforgithub.R
import com.adam_lincoln.android.arrowforgithub.presentation.BaseActivity
import com.adam_lincoln.android.arrowforgithub.presentation.home.HomeActivity

class SplashActivity : BaseActivity(), SplashContract.View {

    // https://android.jlelse.eu/the-complete-android-splash-screen-guide-c7db82bce565
    // Detect if there is a connection:
    // If yes, clear cache and load resources.
    // If no, route to next activity, display snackbar and display cached data while attempting to load from internet.

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
        runOnUiThread { Handler().postDelayed({ presenter.onTimeCompleted() }, 1000) }
    }

    override fun startHomeActivity() {
        // HomeActivity.start(this)
        // https://developer.android.com/guide/components/activities/tasks-and-back-stack
        startActivity(Intent(baseContext, HomeActivity::class.java))
        finish()
    }

}
