package com.adam_lincoln.android.arrowforgithub.presentation.app

import android.app.Application
import com.adam_lincoln.android.arrowforgithub.BuildConfig
import com.adam_lincoln.android.arrowforgithub.R
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)  // See: chrome://inspect/#devices
        }

        ViewPump.init(
            ViewPump.builder().addInterceptor(
                CalligraphyInterceptor(
                    CalligraphyConfig.Builder().setDefaultFontPath(
                        "fonts/Roboto-Regular.ttf"
                    ).setFontAttrId(R.attr.fontPath).build()
                )
            ).build()
        )
    }
}
