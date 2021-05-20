package com.kc.search.response

import com.kc.library.base.response.BaseResponse

/**
 * @data on 5/19/21 3:20 PM
 * @auther KC
 * @describe
 */
data class HotKeyResponse(
    val `data`: List<Data>,
): BaseResponse()

data class Data(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)