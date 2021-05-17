package com.kc.library.base.response


/**
 * @data on 4/25/21 4:45 PM
 * @auther KC
 * @describe 网络请求返回基本类型
 */
open class BaseResponse :IResponse {
    /**
     *  error 和 msg这两个字段看接口文档 这里只是做了一个假设
     */
    open var errorCode: Int = 0
    open var errorMsg: String = ""
    override fun isTokenError(): Boolean = errorCode == -1001
    override fun isSuccess(): Boolean = errorCode == 0
    override fun getMessage(): String = errorMsg
}