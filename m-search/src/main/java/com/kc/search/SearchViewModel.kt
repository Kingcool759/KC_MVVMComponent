package com.kc.search

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.example.mykotlindemo.utils.toast
import com.kc.library.base.base.BasePageViewModel
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
class SearchViewModel(application: Application,var searchType:Int,var wxId:Int,var key:String) : BasePageViewModel<DataX>(application) {

    var itemsHotKeys = MutableLiveData<List<Data>>()

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

    fun onItemClick(item : DataX){
        ARouter.getInstance().build(RouterActivityPath.WebView.WEBVIEW_ACTIVITY)
            .withString("url",item.link)
            .navigation()
    }

    override fun requestData(page: Int) {
        when(searchType){
            0 -> {
                toast("传递搜索类型值错误！")
            }
            1 -> {
                toast("首页文章搜索")
            }
            2 -> {  //公众号文章搜索
                NetworkPortal.getService(SearchService::class.java)?.getHistoryArticles(wxId,page,key)?.enqueue(
                    LiveDataCallback<WxAccountsDetail>(baseLiveData)
                        .bindLoading()
                        .bindSmartRefresh()
                        .bindStateLayout()
                        .doOnResponseSuccess { call, response ->
                            items.addAll(response.data.datas)
                            if (items.isEmpty()){
                                toast("搜索结果为空！")
                            }
                        }
                )
            }
            3 -> {
                toast("广场文章搜索")
            }
            4 -> {
                toast("项目文章搜索")
            }
        }
    }

    override fun getItemLayoutId(): Int  = R.layout.item_search_wxaccount_layout
}