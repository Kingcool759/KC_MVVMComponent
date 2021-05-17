package com.kc.library.base.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @data on 2020/9/27 2:35 PM
 * @auther
 * @describe
 */
abstract class ApiCallback<T> : Callback<T?> {
    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        val statusCode = response.code()
        if (statusCode in 100..101) {
            onFail("出错了，请重试!!!")
        } else if (statusCode in 300..307) {
            onFail("出错了，请重试!!")
        } else if (statusCode in 400..417) {
            onFail("出错了，请重试!")
        } else if (statusCode in 500..505) {
            if (statusCode == 504) {
                onFail("请求超时，请重试")
            } else {
                onFail("出错了，请重试")
            }
        } else {
            onSuccessful(call, response)
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        onFail(t.message)
    }

    abstract fun onSuccessful(
        call: Call<T?>,
        response: Response<T?>
    )

    abstract fun onFail(message: String?)
}