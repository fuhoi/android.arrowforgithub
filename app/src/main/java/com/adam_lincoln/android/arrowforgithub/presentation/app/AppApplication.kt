package com.adam_lincoln.android.arrowforgithub.presentation.app

import android.app.Application
import com.adam_lincoln.android.arrowforgithub.R
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        Stetho.initializeWithDefaults(this)

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
