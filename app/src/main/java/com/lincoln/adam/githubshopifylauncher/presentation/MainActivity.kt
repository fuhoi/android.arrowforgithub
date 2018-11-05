package com.lincoln.adam.githubshopifylauncher.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lincoln.adam.githubshopifylauncher.R
import com.lincoln.adam.githubshopifylauncher.presentation.repo.RepoFragment
import com.lincoln.adam.githubshopifylauncher.presentation.repo.RepoPresenter
import com.lincoln.adam.githubshopifylauncher.presentation.util.Injection
import com.lincoln.adam.githubshopifylauncher.presentation.util.replaceFragmentInActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Qualifier

class MainActivity : AppCompatActivity() {

    companion object {
        var id: Int = 0
    }

    @Inject
//    @field:Named("Format1")
    @field:ChooseInfoType(InfoType.FORMAT_1)
    lateinit var info1: Info

    @Inject
//    @field:Named("Format2")
    @field:ChooseInfoType(InfoType.FORMAT_2)
    lateinit var info2: Info

    private lateinit var repoPresenter: RepoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val repoFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as RepoFragment?
            ?: RepoFragment.newInstance().also {
                replaceFragmentInActivity(it, R.id.contentFrame)
            }

        repoPresenter = RepoPresenter(Injection.provideRepoRepository(applicationContext), repoFragment)

        DaggerInfoComponent.create().inject(this)

        helloWorld1.text = info1.text
        helloWorld2.text = info2.text
    }
}

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ChooseInfoType(val infoType: InfoType)

enum class InfoType {
    NONE,
    FORMAT_1,
    FORMAT_2
}

@Module
class InfoModule {

    @Provides
//    @Named("Format1")
    @ChooseInfoType(InfoType.FORMAT_1)
    fun providesInfoFormat1(): Info {
        return Info("Hello World! ${MainActivity.id++}")
    }

    @Provides
//    @Named("Format2")
    @ChooseInfoType(InfoType.FORMAT_2)
    fun providesInfoFormat2(): Info {
        return Info("${MainActivity.id++} Hello World! ")
    }

}

data class Info(val text: String)

@Component(modules = [InfoModule::class])
interface InfoComponent {
    fun inject(app: MainActivity)
}
