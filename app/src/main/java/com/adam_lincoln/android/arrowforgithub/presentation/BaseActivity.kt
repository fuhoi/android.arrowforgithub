package com.adam_lincoln.android.arrowforgithub.presentation

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper

abstract class BaseActivity : AppCompatActivity() {

    var isActivityCreated: Boolean = false
    var isActivityStarted: Boolean = false
    var isActivityResumed: Boolean = false

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context))  // Provides Calligraphy.
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isActivityCreated = true
    }

    override fun onDestroy() {
        isActivityCreated = false
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        isActivityStarted = true
    }

    override fun onStop() {
        super.onStop()
        isActivityStarted = false
    }

    override fun onResume() {
        super.onResume()
        isActivityResumed = true
    }

    override fun onPause() {
        isActivityResumed = false
        super.onPause()
    }

}
