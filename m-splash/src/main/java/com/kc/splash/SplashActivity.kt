package com.kc.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.library.base.router.RouterActivityPath

/**
 * 闪屏页
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            ARouter.getInstance().build(RouterActivityPath.Main.HOME_MAIN).navigation()
            finish()
        }, 500)
    }
}