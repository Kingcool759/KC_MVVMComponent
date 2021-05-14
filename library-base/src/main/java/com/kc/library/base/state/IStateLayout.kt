package com.example.mykotlindemo.state

/**
 * @data on 5/6/21 5:45 PM
 * @auther KC from ooftf
 * @describe 加载状态接口
 */
interface IStateLayout {
    /**
     * 空布局
     */
    fun switchToEmptyLayout()

    /**
     * 错误布局
     */
    fun switchToErrorLayout()

    /**
     * 成功
     */
    fun switchToSuccessLayout()

    /**
     * 加载布局
     */
    fun switchToLoadingLayout()

    companion object {

        const val STATE_SUCCESS = 1

        const val STATE_LOADING = 2

        const val STATE_EMPTY = 3

        const val STATE_ERROR = 4

    }
}