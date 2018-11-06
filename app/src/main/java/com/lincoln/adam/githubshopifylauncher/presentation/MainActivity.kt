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
import javax.inject.Scope

class MainActivity : AppCompatActivity() {

    companion object {
        var id: Int = 0
    }

    @Inject
    @field:ChooseInfoType(InfoType.FORMAT_1)
    lateinit var info1: Info

    @Inject
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

        infoScopedComponent = DaggerInfoScopedComponent.create()

        helloWorld3.setOnClickListener { doMagic() }
    }

    private lateinit var infoScopedComponent: InfoScopedComponent

    private fun doMagic() {
        val infoStorage = InfoStorage()
        infoScopedComponent.inject(infoStorage)
        helloWorld3.text = "Unique ${infoStorage.scopedInfo.text}\n" +
                "info1: ${infoStorage.notScopedInfo.text}"
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ChooseInfoType(val infoType: InfoType)

enum class InfoType {
    NONE,
    FORMAT_1,
    FORMAT_2
}

@Module
open class InfoModule {

    @Provides
    @ChooseInfoType(InfoType.FORMAT_1)
    open fun providesInfoFormat1(): Info = Info("Hello World! ${MainActivity.id++}")

    @Provides
    @ChooseInfoType(InfoType.FORMAT_2)
    open fun providesInfoFormat2(): Info = Info("${MainActivity.id++} Hello World!")

}

data class Info(val text: String)

@Component(modules = [InfoModule::class])
interface InfoComponent {
    fun inject(app: MainActivity)
}



@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class InfoScope

@InfoScope
@Component
interface InfoScopedComponent {
    fun inject(infoStorage: InfoStorage)
}

class InfoStorage {

    @Inject
    lateinit var scopedInfo: ScopedInfo

    @Inject
    lateinit var notScopedInfo: NotScopedInfo

}

@InfoScope
class ScopedInfo @Inject constructor() {
    val text: String = "Hello World! ${MainActivity.id++}"
}

class NotScopedInfo @Inject constructor() {
    val text: String = "Hello World! ${MainActivity.id++}"
}
