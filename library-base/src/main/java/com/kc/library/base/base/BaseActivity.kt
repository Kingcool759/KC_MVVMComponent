package com.kc.library.base.base;

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.gyf.immersionbar.ImmersionBar
import com.kc.library.base.R

/**
 * @data on 5/6/21 4:00 PM
 * @auther KC
 * @describe 实现了沉浸式状态栏+竖屏
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (isScreenForcePortrait()){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        super.onCreate(savedInstanceState)
    }

    init {
        if (isImmersionEnable()){
            lifecycle.addObserver(object : LifecycleObserver{
                @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
                fun onCreate() {
                    ImmersionBar.with(this@BaseActivity)
                        .statusBarDarkFont(isDarkFont())
                        .fitsSystemWindows(isFitsSystemWindows())
                        .statusBarColorInt(setStatusBarColorInt())
                        .keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                        .init()
                }
            })
        }
    }

    /**
     * 是否强制竖屏
     */
    fun isScreenForcePortrait() = true

    /**
     * 用来控制 是否要做状态栏的改变(沉浸式)
     */
    open fun isImmersionEnable(): Boolean {
        return true
    }
    open fun isDarkFont(): Boolean {
        return true
    }

    /**
     * 设置状态栏的颜色
     */
    open fun setStatusBarColorInt(): Int {
        return resources.getColor(R.color.base_bg_transparent)  //全透明
    }

    /**
     * 为了防止布局和顶部的状态栏重叠
     */
    open fun isFitsSystemWindows(): Boolean {
        return true   //false表明占据状态栏,true不占据
    }

}