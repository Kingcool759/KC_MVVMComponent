package com.kc.search

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.library.base.base.BaseViewModel
import com.kc.library.base.callback.LiveDataCallback
import com.kc.library.base.network.NetworkPortal
import com.kc.library.base.router.RouterActivityPath
import com.kc.search.response.Data
import com.kc.search.response.HotKeyResponse
import com.kc.wx.response.DataX
import com.kc.wx.response.WxAccountsDetail
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @data on 5/19/21 2:35 PM
 * @auther KC
 * @describe 搜索功能
 */
class SearchViewModel(application: Application,var WxId:Int,var pageNo:Int,var key:String) : BaseViewModel(application) {

    var itemsHotKeys = MutableLiveData<List<Data>>()

    var items = ObservableArrayList<DataX>()
    val itemBinding = ItemBinding.of<DataX>(BR.item, R.layout.item_search_wxaccount_layout)
        .bindExtra(BR.viewModel, this)

    init {
        getHotKey()
    }

    fun getHotKey() {
        NetworkPortal.getService(SearchService::class.java)?.getHotKey()?.enqueue(
            LiveDataCallback<HotKeyResponse>(baseLiveData)
                .doOnResponseSuccess { call, response ->
                    itemsHotKeys.postValue(response.data)
                }
        )
    }
    fun getHistoryArticles(){
        NetworkPortal.getService(SearchService::class.java)?.getHistoryArticles(WxId,pageNo,key)?.enqueue(
            LiveDataCallback<WxAccountsDetail>(baseLiveData)
                .doOnResponseSuccess { call, response ->
                    items.addAll(response.data.datas)
                }
        )
    }

    fun onItemClick(item : DataX){
        ARouter.getInstance().build(RouterActivityPath.WebView.WEBVIEW_ACTIVITY)
            .withString("url",item.link)
            .navigation()
    }
}