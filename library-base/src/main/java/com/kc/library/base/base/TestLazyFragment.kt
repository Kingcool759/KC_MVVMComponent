package com.kc.library.base.base;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * @data on 4/28/21 9:58 AM
 * @auther KC
 * @describe BaseFragment，实现了Fragment懒加载
 */
open class TestLazyFragment : Fragment() {

    //表明fragment是否显示
    private var mIsVisibleToUser = false

    //表明view是否被加载
    private var mIsViewCreated = false

    //表明data是否被加载
    private var mIsDataLoaded = false

    /**
     * 是否view已被加载
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mIsViewCreated = true
        //为了保证第一个fragment可见后加载数据，因为setUserVisibleHint() 要比 onViewCreated()要早执行.所以，fragment1数据无法被加载，当条件改变时，就需要懒加载。
        lazyLoad()
    }

    /**
     * 懒加载的关键
     * isVisibleToUser是否为true，则对应表明页面是否展示到当前Fragment。
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mIsVisibleToUser = isVisibleToUser
        lazyLoad()  //fragment1并不满足mIsViewCreated
    }

    /**
     * view和data清除
     */
    override fun onDestroyView() {
        super.onDestroyView()
        mIsViewCreated = false
        mIsDataLoaded = false
    }

    fun lazyLoad() {
        if (mIsViewCreated && mIsVisibleToUser && !mIsDataLoaded) {
            initData()
            mIsDataLoaded = true
        }
    }

    //开放要加载的数据
    open fun initData() {}
}