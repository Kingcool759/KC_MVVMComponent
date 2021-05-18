package com.kc.square

import com.kc.square.response.SquareArticles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @data on 5/18/21 12:20 PM
 * @auther KC
 * @describe
 */
interface SquareService {
    @GET(SquareApi.SQUARE_LIST)
    fun getSquareList(@Path("page") pageNo: Int): Call<SquareArticles>
}