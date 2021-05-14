package com.kc.library.base.livedata;

import android.os.Handler
import android.os.Looper

/**
 * @data on 5/6/21 5:44 PM
 * @auther
 * @describe
 */
object ThreadUtil {
    private val mainHandler = Handler(Looper.getMainLooper())
    private val isMainThread: Boolean
        get() = Thread.currentThread() == Looper.getMainLooper().thread

    fun runOnUiThread(runnable: Runnable) {
        if (isMainThread) {
            runnable.run()
        } else {
            mainHandler.post(runnable)
        }
    }

    fun postOnUiThread(runnable: Runnable) {
        mainHandler.post(runnable)
    }
}