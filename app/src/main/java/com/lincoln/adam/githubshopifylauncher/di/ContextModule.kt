package com.lincoln.adam.githubshopifylauncher.di

import android.app.Application
import android.content.Context
import android.view.View
import dagger.Module
import dagger.Provides

@Module
class ContextModule {

    @Provides
    fun providesContext(view: View): Context = view.context

    @Provides
    fun providesApplication(context: Context): Application = context.applicationContext as Application

}
