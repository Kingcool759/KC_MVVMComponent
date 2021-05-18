package com.kc.wx

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.kc.library.base.base.BaseViewModel
import com.kc.library.base.callback.LiveDataCallback
import com.kc.library.base.network.NetworkPortal
import com.kc.wx.response.Account
import com.kc.wx.response.WxAccountsResp

/**
 * @data on 5/17/21 4:31 PM
 * @auther KC
 * @describe  获取公众号名称
 */
class WxViewModel(application: Application): BaseViewModel(application) {

    val items = MutableLiveData<List<Account>>()

    fun getWxCodeList(){
        NetworkPortal.getService(WxService::class.java)?.getOfficialAccounts()?.enqueue(
            LiveDataCallback<WxAccountsResp>(baseLiveData)
                .doOnResponseSuccess { _, response ->
                    items.postValue(response.data)
                })
    }

}