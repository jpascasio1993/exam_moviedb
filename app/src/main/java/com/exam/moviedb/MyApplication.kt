package com.exam.moviedb

import android.app.Application
import com.exam.moviedb.di.DIModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(DIModule.modules)
        }
    }
}