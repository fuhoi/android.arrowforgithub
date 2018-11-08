package com.lincoln.adam.githubshopifylauncher.presentation.home

import android.os.Bundle
import com.lincoln.adam.githubshopifylauncher.R
import com.lincoln.adam.githubshopifylauncher.presentation.repo.RepoFragment
import com.lincoln.adam.githubshopifylauncher.presentation.util.replaceFragmentInActivity
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector {

//    @Inject
//    lateinit var repoPresenter: RepoPresenter

    @Inject
    lateinit var repoFragmentProvider: Lazy<RepoFragment>

//    @Inject
//    @field:ChooseInfoType(InfoType.FORMAT_1)
//    lateinit var info1: Info
//
//    @Inject
//    @field:ChooseInfoType(InfoType.FORMAT_2)
//    lateinit var info2: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val repoFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as RepoFragment?
            ?: repoFragmentProvider.get().also {
                replaceFragmentInActivity(it, R.id.contentFrame)
            }

//        DaggerInfoComponent.create().inject(this)

//        helloWorld1.text = info1.text
//        helloWorld2.text = info2.text

//        helloWorld3.setOnClickListener { doMagic() }

//        singletonComponent = DaggerSingletonComponent.create()

//        infoScopedComponent = singletonComponent.getInfoScopedComponent()
    }

//    private lateinit var infoScopedComponent: InfoScopedComponent
//
//    private lateinit var singletonComponent: SingletonComponent
//
//    private fun doMagic() {
////        val infoScopedComponent = singletonComponent.getInfoScopedComponent()
////        infoScopedComponent = singletonComponent.getInfoScopedComponent()
//        val infoStorage = InfoStorage()
//        infoScopedComponent.inject(infoStorage)
//        helloWorld3.text = "Singleton: ${infoStorage.singletonOne.text}\nUnique ${infoStorage.scopedInfo.text}\n" +
//                "info1: ${infoStorage.notScopedInfo.text}"
//    }
}

//var id: Int = 0
//
//@Qualifier
//@Retention(AnnotationRetention.RUNTIME)
//annotation class ChooseInfoType(val infoType: InfoType)
//
//enum class InfoType {
//    NONE,
//    FORMAT_1,
//    FORMAT_2
//}
//
//@Module
//open class InfoModule {
//
//    @Provides
//    @ChooseInfoType(InfoType.FORMAT_1)
//    open fun providesInfoFormat1(): Info =
//        Info("Hello World! ${id++}")
//
//    @Provides
//    @ChooseInfoType(InfoType.FORMAT_2)
//    open fun providesInfoFormat2(): Info =
//        Info("${id++} Hello World!")
//
//}
//
//data class Info(val text: String)
//
//@Component(modules = [InfoModule::class])
//interface InfoComponent {
//    fun inject(app: HomeActivity)
//}
//
//
//
//@Scope
//@Retention(AnnotationRetention.RUNTIME)
//annotation class InfoScope
//
//@InfoScope
////@Component
//@Subcomponent
//interface InfoScopedComponent {
//    fun inject(infoStorage: InfoStorage)
//}
//
//class InfoStorage {
//
//    @Inject
//    lateinit var singletonOne: SingletonOne
//
//    @Inject
//    lateinit var scopedInfo: ScopedInfo
//
//    @Inject
//    lateinit var notScopedInfo: NotScopedInfo
//
//}
//
//@InfoScope
//class ScopedInfo @Inject constructor() {
//    val text: String = "Hello World! ${id++}"
//}
//
//class NotScopedInfo @Inject constructor() {
//    val text: String = "Hello World! ${id++}"
//}
//
//@Singleton
//class SingletonOne @Inject constructor() {
//    val text: String = "Singleton! ${id++}"
//}
//
//@Singleton
//@Component
//interface SingletonComponent {
//    fun getInfoScopedComponent(): InfoScopedComponent
//}
