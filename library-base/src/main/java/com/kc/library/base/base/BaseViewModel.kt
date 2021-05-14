package com.kc.library.base.base;

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.kc.library.base.livedata.BaseLiveData
import java.lang.ref.WeakReference

/**
 * @data on 5/6/21 5:38 PM
 * @auther KC
 * @describe
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var baseLiveData = BaseLiveData()

    private var mActivityWeakReference: WeakReference<Activity>? = null

    private var mFragmentWeakReference: WeakReference<Fragment>? = null

    /**
     * 用于绑定到xml布局上的 （BindingAdapter）
     */
    fun getStateLayout() = baseLiveData.stateLayout

    fun getRefreshState() = baseLiveData.smartRefresh

    fun getLoadMoreState() = baseLiveData.smartLoadMore


    /**
     * 设置Activity
     */
    fun setActivity(activity: Activity) {
        mActivityWeakReference = WeakReference(activity)
    }

    /**
     * 获取到Activity
     */
    fun getActivity(): Activity? {
        return mActivityWeakReference?.get()
    }

    /**
     * 设置Fragment
     */
    fun setFragment(fragment: Fragment) {
        mFragmentWeakReference = WeakReference(fragment)
    }

    /**
     * 获取到Fragment
     */
    fun getFragment(): Fragment? {
        return mFragmentWeakReference?.get()
    }
}