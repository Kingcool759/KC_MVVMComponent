package com.kc.library.base.base

import android.app.Application

/**
 * @data on 5/14/21 4:10 PM
 * @auther
 * @describe
 */
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        lateinit var instance: Application
    }
}