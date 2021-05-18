package com.kc.wx.response

import com.kc.library.base.response.BaseResponse
/**
 * @data on 4/25/21 4:47 PM
 * @auther KC
 * @describe wanAndroid网站：微信公众号列表
 */
data class WxAccountsResp(
    val `data`: List<Account>
):BaseResponse()

data class Account(
    val children: List<String>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)

