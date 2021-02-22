package com.exam.moviedb

import android.app.Application
import com.exam.moviedb.di.DIModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger


class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            logger(PrintLogger(Level.INFO))
            modules(listOf(DIModule.appModules))
        }
    }
}