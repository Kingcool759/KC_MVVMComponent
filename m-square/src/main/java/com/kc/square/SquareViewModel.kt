package com.kc.square

import android.app.Application
import com.kc.library.base.base.BasePageViewModel
import com.kc.library.base.callback.LiveDataCallback
import com.kc.library.base.network.NetworkPortal
import com.kc.square.response.DataX
import com.kc.square.response.SquareArticles

/**
 * @data on 5/18/21 12:24 PM
 * @auther KC
 * @describe
 */
class SquareViewModel(application: Application) :BasePageViewModel<DataX>(application) {

    init {
        refresh()
    }

    override fun requestData(page: Int) {
        NetworkPortal.getService(SquareService::class.java)?.getSquareList(page)?.enqueue(
            LiveDataCallback<SquareArticles>(baseLiveData)
                .bindLoading()
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    handleItemData(page, response.data.datas)
                })
    }

    override fun getItemLayoutId(): Int {
        return R.layout.item_square_article_list
    }
}