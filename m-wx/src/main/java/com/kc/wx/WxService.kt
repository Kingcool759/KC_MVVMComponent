package com.kc.wx

import com.kc.library.base.network.AppApi
import com.kc.wx.response.WxAccountsResp
import retrofit2.Call
import retrofit2.http.GET

/**
 * @data on 5/14/21 4:27 PM
 * @auther KC
 * @describe
 */
interface WxService {
    /**
     * 获取wanandroid公众号标题列表
     */
    @GET(WxApi.OFFITIAL_ACCOUNTS)
    fun getOfficialAccounts(): Call<WxAccountsResp?>?
}