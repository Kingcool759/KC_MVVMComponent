package com.kc.wx.response

import com.kc.library.base.response.BaseResponse
/**
 * @data on 4/25/21 4:47 PM
 * @auther KC
 * @describe wanAndroid网站：微信公众号列表
 */
class WxAccountsResp : BaseResponse() {
    var data: List<DataBean>? = null

    class DataBean {
        var courseId: String? = null
        var id: String? = null
        var name: String? = null
        var order: String? = null
        var parentChapterId: String? = null
        var userControlSetTop: String? = null
        var visible: String? = null
    }
}