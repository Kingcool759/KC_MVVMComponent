package com.kc.library.base.router

/**
 * 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by goldze on 2018/6/21
 */
class RouterFragmentPath {
    /**
     * 1、首页组件
     */
    object Home {
        private const val HOME = "/home"
        const val HOME_FRAGMENT = "$HOME/home_fragment"
    }

    /**
     * 1、公众号组件
     */
    object Wx {
        private const val WX = "/wx"
        const val WX_FRAGMENT = "$WX/wx_fragment"
        const val WX_ACCOUNTS = "$WX/wx_account_fragment"
    }

    /**
     * 3、广场组件
     */
    object Square {
        private const val SQUARE = "/square"
        const val SQUARE_FRAGMENT = "$SQUARE/square_fragment"
    }

    /**
     * 4、项目组件
     */
    object Project {
        private const val PROJECT = "/project"
        const val PROJECT_FRAGMENT = "$PROJECT/project_fragment"
    }

    /**
     * 5、用户组件
     */
    object User {
        private const val USER = "/user"
        const val MINE_FRAGMENT = "$USER/mine_fragment"
    }
}