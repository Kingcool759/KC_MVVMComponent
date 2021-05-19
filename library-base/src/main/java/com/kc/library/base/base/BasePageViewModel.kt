package com.kc.library.base.base;

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.kc.library.base.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding


/**
 * @data on 5/7/21 6:11 PM
 * @auther KC
 * @describe 自动加载页面数据
 */
abstract class BasePageViewModel<T>(application: Application) : BaseViewModel(application) {


    /**
     * 用于装载item的数据
     */
    val items = ObservableArrayList<T>()

    val itemBinding by lazy {
        ItemBinding.of<T>(BR.item, getItemLayoutId()).bindExtra(BR.viewModel, this)
    }

    /**
     * 每页要拉取的数据量
     */
    open fun getPageSize() = 20

    /**
     * 默认是从0 开始的,子类可以重写，指定开始的页码
     */
    open fun getStartPageNum() = 0

    /**
     * 子类用于实现请求数据
     */
    abstract fun requestData(page: Int)

    fun refresh() {
        requestData(getStartPageNum())
    }

    /**
     * 加载更多
     */
    fun loadMore() {
        requestData(items.size / getPageSize() + getStartPageNum())
    }

    /**
     * 处理数据的
     */
    fun handleItemData(page: Int, data: List<T>) {
        /**
         * 当是下拉刷新的话
         */
        if (page == getStartPageNum()) {
            items.clear()
        }
        items.addAll(data)
        /**
         * 当第一次请求没有数据的时候，就显示空数据页面
         */
        if (items.isEmpty()) {
            baseLiveData.switchEmpty()
        }

        /**
         * 上滑加载到最后一页了。
         */
        if (data.size<getPageSize()) {
            baseLiveData.finishLoadMoreWithNoMoreData()
        }
    }

    /**
     * 获取item的布局ID
     */
    abstract fun getItemLayoutId(): Int
}