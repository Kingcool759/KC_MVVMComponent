package com.kc.home

import com.kc.home.response.HomeArticlesResp
import com.kc.library.base.network.AppApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @data on 5/14/21 4:23 PM
 * @auther
 * @describe
 */
interface HomeService {

    val baseUrl: String get() = AppApi.BaseURL

    /**
     * 获取wanandroid首页文章列表
     */
    @GET(HomeApi.HOME_ARTICLES)
    fun getHomeArticles(): Call<HomeArticlesResp>

    /**
     * 获取wanandroid首页文章列表2（根据page加载数据）
     */

    @GET(HomeApi.HOME_ARTICLES_PAGE)
    fun getHomeArticles2(@Path("page") pageNo: Int): Call<HomeArticlesResp>
}