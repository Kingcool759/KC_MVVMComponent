package com.kc.search

import com.kc.search.response.HotKeyResponse
import com.kc.wx.response.WxAccountsDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @data on 5/19/21 3:17 PM
 * @auther
 * @describe
 */
interface SearchService {

    @GET(SearchApi.HOT_KEY)
    fun getHotKey(): Call<HotKeyResponse>

    @GET(SearchApi.HISTORY_ARTICLES)  //依赖于模块：m-wx
    fun getHistoryArticles(
        @Path("id") idNo: Int,
        @Path("page") pageNo: Int,
        @Query("k") key: String
    ): Call<WxAccountsDetail>
}