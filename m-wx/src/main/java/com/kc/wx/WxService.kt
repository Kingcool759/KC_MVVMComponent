package com.kc.wx

import com.kc.library.base.network.AppApi
import com.kc.wx.response.WxAccountsDetail
import com.kc.wx.response.WxAccountsResp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
    fun getOfficialAccounts(): Call<WxAccountsResp>

    /**
     * 公众号列表
     */
    @GET(WxApi.ACCOUNT_DETAIL)
    fun getAccountDetails(@Path("id") id: Int, @Path("page") pageNo: Int): Call<WxAccountsDetail>
}