package com.kc.wx.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.example.mykotlindemo.utils.toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kc.library.base.BR
import com.kc.library.base.base.BasePageViewModel
import com.kc.library.base.callback.LiveDataCallback
import com.kc.library.base.network.NetworkPortal
import com.kc.library.base.router.RouterActivityPath
import com.kc.wx.R
import com.kc.wx.WxService
import com.kc.wx.response.DataX
import com.kc.wx.response.WxAccountsDetail
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @data on 5/17/21 5:43 PM
 * @auther KC
 * @describe
 */
class WxAccountViewModel(application: Application,var id: Int) : BasePageViewModel<DataX>(application) {

    var finishLoad = MutableLiveData(false)

    val skeleton = ItemBinding.of<String>(BR.item, R.layout.item_wxaccount_skeleton)
        .bindExtra(BR.viewModel, this)
    val skeletonItem = ObservableArrayList<DataX>()

    init {
        initSkeleton()
        refresh()
    }

    override fun requestData(page: Int) {
        NetworkPortal.getService(WxService::class.java)?.getAccountDetails(id,page)?.enqueue(
            LiveDataCallback<WxAccountsDetail>(baseLiveData)
                .bindLoading()
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    handleItemData(page, response.data.datas)
                    finishLoad.postValue(true)
                }
                .doOnAnyFail { toast("你的网络似乎出了点问题喔～") }
        )
    }

    override fun getItemLayoutId(): Int {
        return R.layout.item_wxaccount_layout
    }

    fun onItemClick(item : DataX){
        ARouter.getInstance().build(RouterActivityPath.WebView.WEBVIEW_ACTIVITY)
            .withString("url",item.link)
            .navigation()
    }

    fun initSkeleton() {
        val json = "[{},{},{},{},{},{},{}]"
        skeletonItem.addAll(Gson().fromJson<ArrayList<DataX>>(json, object :
            TypeToken<ArrayList<DataX>>() {}.type))
    }
}