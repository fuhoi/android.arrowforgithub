package com.lincoln.adam.githubshopifylauncher.data.source.remote

import com.lincoln.adam.githubshopifylauncher.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class GitHubDeveloperApiModule {

    companion object {
        const val GITHUB_BASE_URL = "https://api.github.com/"
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            builder.addInterceptor(logging)
        }

        return builder.build()
    }

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient())
            .build()
    }

    @Provides
    fun providesGitHubDeveloperApiService(): GitHubDeveloperApiService {
        return providesRetrofit().create(GitHubDeveloperApiService::class.java)
    }

}
