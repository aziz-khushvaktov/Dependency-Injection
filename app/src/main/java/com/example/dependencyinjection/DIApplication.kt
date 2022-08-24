package com.example.dependencyinjection

import android.app.Application
import com.example.dependencyinjection.utils.SampleClass
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DIApplication: Application() {
    @Inject
    lateinit var sampleClass: SampleClass

    override fun onCreate() {
        super.onCreate()
        //sampleClass.doSomething()
    }
}