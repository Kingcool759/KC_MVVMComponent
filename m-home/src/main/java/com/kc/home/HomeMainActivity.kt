package com.kc.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kc.library.base.router.RouterActivityPath
import com.kc.library.base.router.RouterFragmentPath

@Route(path = RouterActivityPath.Main.HOME_MAIN)
class HomeMainActivity : AppCompatActivity() {
    private val mFragments = ArrayList<Fragment>()
    private var mBottomNav: BottomNavigationView? = null
    private var mPreFragmentFlag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_main)
        initView()
        initFragment()
        selectFragment()
    }

    private fun initView() {
        mBottomNav = findViewById(R.id.mBottomNav)
    }

    private fun initFragment() {
        mFragments.add(ARouter.getInstance().build(RouterFragmentPath.Home.HOME_FRAGMENT).navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build(RouterFragmentPath.Wx.WX_FRAGMENT).navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build(RouterFragmentPath.Square.SQUARE_FRAGMENT).navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build(RouterFragmentPath.Project.PROJECT_FRAGMENT).navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build(RouterFragmentPath.User.MINE_FRAGMENT).navigation() as Fragment)
        initLoadFragment(R.id.mContainerView, 0, mFragments)
    }

    // 参数一 是一个FrameLayout的ID，用来动态加载Fragment，
    private fun initLoadFragment(containerId: Int, showFragment: Int, fragments: ArrayList<Fragment>) {
        //获取到FragmentManager实例的同时去开启事物
        val transaction = supportFragmentManager.beginTransaction()
        for (i in 0 until fragments.size) {
            //首先将Fragment添加到事务中
            transaction.add(containerId, fragments[i], fragments[i].javaClass.name)
            //默认展示 fragments[showFragment]
            //这里做首次Fragment的展示，如果不是指定的Fragment就先隐藏，需要的时候再显示出来
            if (i != showFragment) transaction.hide(fragments[i])
        }
        //提交事物
        transaction.commitAllowingStateLoss()
    }

    private fun selectFragment() {
        //注册监听事件
        mBottomNav!!.itemIconTintList = null
        mBottomNav!!.setOnNavigationItemSelectedListener(com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    showAndHideFragment(mFragments[0], mFragments[mPreFragmentFlag])
                    mPreFragmentFlag = 0
                }
                R.id.wechat -> {
                    showAndHideFragment(mFragments[1], mFragments[mPreFragmentFlag])
                    mPreFragmentFlag = 1
                }
                R.id.project -> {
                    showAndHideFragment(mFragments[2], mFragments[mPreFragmentFlag])
                    mPreFragmentFlag = 2
                }
                R.id.system -> {
                    showAndHideFragment(mFragments[3], mFragments[mPreFragmentFlag])
                    mPreFragmentFlag = 3
                }
                R.id.setting -> {
                    showAndHideFragment(mFragments[4], mFragments[mPreFragmentFlag])
                    mPreFragmentFlag = 4
                }
            }
            true
        })
    }

    //加载不同的Fragment
    private fun showAndHideFragment(show: Fragment?, hide: Fragment?) {
        val transaction = supportFragmentManager.beginTransaction()
        if (show !== hide) transaction.show(show!!).hide(hide!!).commitAllowingStateLoss()
    }
}