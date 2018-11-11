package com.adam_lincoln.android.arrowforgithub.presentation.app

import android.app.Application
import com.adam_lincoln.android.arrowforgithub.R
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

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
