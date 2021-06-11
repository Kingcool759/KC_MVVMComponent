package com.kc.debug.tabscroll

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.androidkun.xtablayout.XTabLayout
import com.kc.debug.databinding.ScrollActivityTabBinding
import com.kc.library.base.base.BaseMvvmActivity
import com.kc.library.base.router.RouterActivityPath

/**
 * @author 
 * @email 
 * @date 2021-06-11
 */
@Route(path = RouterActivityPath.Debug.TAB_SCOLL_ACTIVITY)
class TabActivity : BaseMvvmActivity<ScrollActivityTabBinding, TabViewModel>() {

    val rvManager by lazy {
        dataBinding.recyclerView.layoutManager as LinearLayoutManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTayLayout()
        setRvScroll()
    }

    private fun setTayLayout(){
        dataBinding.tabLayout.setScrollPosition(0, 0f, true)
        viewModel.tabs.forEach {
            dataBinding.tabLayout.addTab(dataBinding.tabLayout.newTab().setText(it.name.value))
        }
        switchTabLayoutMode(viewModel.tabs.size)
        dataBinding.tabLayout.setScrollPosition(0, 0f, true)
        rvManager.scrollToPositionWithOffset(0, 0)
        //tab点击事件
        dataBinding.tabLayout.addOnTabSelectedListener(object : XTabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: XTabLayout.Tab?) {}

            override fun onTabUnselected(tab: XTabLayout.Tab?) {}

            override fun onTabSelected(tab: XTabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        rvManager.scrollToPositionWithOffset(0, 0)
                    }
                    else -> {
                        tab?.let {
                            val currentTabPosition = it.position - 1
                            rvManager.scrollToPositionWithOffset(currentTabPosition ?: 0, 0)
                        }
                    }
                }

            }
        })
    }

    private fun setRvScroll() {
        if (Build.VERSION.SDK_INT >= 23) {   //23版本才有此方法
            dataBinding.recyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val position = rvManager.findFirstVisibleItemPosition() + 1  //有个全部，所以位置+1
                dataBinding.tabLayout.setScrollPosition(position, 0f, true)
            }
        } else {
            dataBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val position = rvManager.findFirstVisibleItemPosition() + 1
                    dataBinding.tabLayout.setScrollPosition(position, 0f, true)
                }
            })
        }
    }

    private fun switchTabLayoutMode(size: Int) {
        if (size < 7) {
            dataBinding.tabLayout.tabMode = XTabLayout.MODE_FIXED  //设置tab模式：固定
        } else {
            dataBinding.tabLayout.tabMode = XTabLayout.MODE_SCROLLABLE  //设置tab模式：可滑动
        }
    }
}