package com.lincoln.adam.githubshopifylauncher

import com.lincoln.adam.githubshopifylauncher.presentation.home.*
import dagger.Component
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

//class TestMainActivity {
//
//    companion object {
//        var id: Int = 0
//    }
//
//    @Inject
//    @field:ChooseInfoType(InfoType.FORMAT_1)
//    lateinit var info1: Info
//
//    @Inject
//    @field:ChooseInfoType(InfoType.FORMAT_2)
//    lateinit var info2: Info
//
//    @Before
//    fun beforeEachTest() {
//        id = 0
//        DaggerTestInfoComponent.builder().infoModule(TestInfoModule()).build().inject(this)
//    }
//
//    @Test
//    fun simpleTest() {
//        assertEquals("Hello World! 0", info1.text)
//        assertEquals("1 Hello World!", info2.text)
//    }
//
//    @Test
//    fun simplePass() {
//        assertTrue(true)
//    }
//
//    @Test
//    fun simpleFail() {
//        assertTrue(false)
//    }
//}
//
//class TestInfoModule: InfoModule() {
//
//    override fun providesInfoFormat1(): Info =
//        Info("Hello World! ${TestMainActivity.id++}")
//
//    override fun providesInfoFormat2(): Info =
//        Info("${TestMainActivity.id++} Hello World!")
//
//}
//
//@Component(modules = [InfoModule::class])
//interface TestInfoComponent : InfoComponent {
//    fun inject(app: TestMainActivity)
//}
