package com.example.mykotlindemo.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.ContextThemeWrapper
import androidx.fragment.app.FragmentActivity

/**
 * @data on 4/27/21 3:20 PM
 * @auther
 * @describe
 */
object ContextUtils {
    /**
     * 如果你确定 context是activity 可以使用此方法
     *
     * @param context
     * @return
     */
    fun toActivity(context: Context?): Activity? {
        if (context is Activity) {
            return context
        } else if (context is ContextWrapper) {
            return toActivity((context as ContextThemeWrapper).baseContext)
        }
        return null
    }

    fun toFragmentActivity(context: Context?): FragmentActivity? {
        if (context is Activity) {
            return context as FragmentActivity?
        } else if (context is ContextWrapper) {
            return toFragmentActivity((context as ContextThemeWrapper).baseContext)
        }
        return null
    }
}