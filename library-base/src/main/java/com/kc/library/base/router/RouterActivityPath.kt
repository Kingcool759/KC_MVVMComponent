package com.kc.library.base.router

/**
 * 用于组件开发中，ARouter单Activity跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by goldze on 2018/6/21
 */
class RouterActivityPath {
    /**
     * 主业务组件
     */
    object Main {
        private const val MAIN = "/main"
        const val HOME_MAIN = "$MAIN/home_main"  //home中做BottomNavigation
    }

    /**
     * 登陆组件
     */
    object Login {
        private const val Login = "/login"
    }

    /**
     * webView组件
     */
    object WebView{
        private const val WEBVIEW = "/webview"
        const val WEBVIEW_ACTIVITY = "$WEBVIEW/webview_activity"
    }
}