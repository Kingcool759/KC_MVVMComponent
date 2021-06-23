package com.kc.mvvmcomponent

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.library.base.BuildConfig
import com.kc.library.base.base.BaseApplication
import com.ooftf.director.app.Director
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

open class AppApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
            // 打印日志
            ARouter.openLog()
        }
        //1、路由初始化
        ARouter.init(this)  //凡是app组件中用到的ARouter，必须要保证它先都进行了初始化，因此不能放进子线程中。
        //2、didi-Dokit
        Director.init("f6637fc66d9b4096acdd5c13bf2061b6", false)

        /**
         * 启动优化
         */
        Thread {
            //设置子线程的优先级，不与主线程抢资源（必须设置优先级，不然会争抢主线程资源，无法优化启动时间）
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)
            //子线程初始化第三方组件******

            //3、自定义炫酷Logger拦截网络请求
            val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true) // (Optional) Whether to show thread info or not. Default true
                .methodCount(5) // (Optional) How many method line to show. Default 2
                .methodOffset(7) // (Optional) Hides internal method calls up to offset. Default 5
                .tag("My custom tag") // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
            Logger.addLogAdapter(AndroidLogAdapter(formatStrategy)) // 初始化Logger

//            Thread.sleep(500) //建议延迟初始化，可以发现是否影响其它功能，或者是崩溃！
        }.start()

        instance = this


        //打印被创建的Activity名称
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.e("ssss", activity.javaClass.name)
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

        })
    }

    companion object {
        lateinit var instance: Application
    }
}