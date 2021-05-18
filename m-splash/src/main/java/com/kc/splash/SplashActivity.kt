package com.kc.splash

import android.os.Bundle
import android.os.Handler
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.library.base.base.BaseActivity
import com.kc.library.base.router.RouterActivityPath

/**
 * 闪屏页
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            ARouter.getInstance().build(RouterActivityPath.Main.HOME_MAIN).navigation()
            finish()
        }, 500)
    }
}