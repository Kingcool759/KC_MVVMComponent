package com.kc.m_search

import com.kc.m_search.response.HotKeyResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * @data on 5/19/21 3:17 PM
 * @auther
 * @describe
 */
interface SearchService {

    @GET(SearchApi.HOT_KEY)
    fun getHotKey():Call<HotKeyResponse>
}