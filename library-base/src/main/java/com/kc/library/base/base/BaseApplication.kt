package com.kc.library.base.base

import android.annotation.SuppressLint
import android.app.Application
import android.webkit.WebChromeClient
import android.webkit.WebView

/**
 * @data on 5/14/21 4:10 PM
 * @auther KC
 * @describe  实现WebView预加载
 */
open class BaseApplication : Application() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate() {
        super.onCreate()
        //webView预加载-初始化
        webView = WebView(this)
        instance = this
    }
    companion object {
        lateinit var instance: Application
        @SuppressLint("StaticFieldLeak")
        lateinit var webView:WebView
    }
}