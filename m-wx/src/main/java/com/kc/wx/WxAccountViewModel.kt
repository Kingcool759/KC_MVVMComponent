package com.kc.wx

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kc.library.base.base.BasePageViewModel
import com.kc.library.base.callback.LiveDataCallback
import com.kc.library.base.network.NetworkPortal
import com.kc.wx.response.DataX
import com.kc.wx.response.WxAccountsDetail

/**
 * @data on 5/17/21 5:43 PM
 * @auther KC
 * @describe
 */
class WxAccountViewModel(application: Application,var id: Int) : BasePageViewModel<DataX>(application) {

    init {
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
                })

    }

    override fun getItemLayoutId(): Int {
        return R.layout.item_wx_account
    }

    fun onItemClick(item : DataX){

    }
}