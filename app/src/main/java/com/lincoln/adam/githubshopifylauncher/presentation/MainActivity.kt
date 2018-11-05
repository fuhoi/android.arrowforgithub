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

class MainActivity : AppCompatActivity() {

    companion object {
        var id: Int = 0
    }

    @Inject
    lateinit var info1: Info

    @Inject
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

@Module
class InfoModule {
    @Provides
    fun providesInfo(): Info {
        return Info("Hello World! " + MainActivity.id++)
    }
}

data class Info(val text: String)

@Component(modules = [InfoModule::class])
interface InfoComponent {
    fun inject(app: MainActivity)
}
