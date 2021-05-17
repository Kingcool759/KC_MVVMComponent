package com.kc.home

import android.app.Application
import com.kc.library.base.callback.LiveDataCallback
import com.kc.home.response.DataX
import com.kc.home.response.HomeArticlesResp
import com.kc.library.base.base.BasePageViewModel
import com.kc.library.base.network.NetworkPortal

/**
 * @data on 5/14/21 4:40 PM
 * @auther
 * @describe
 */
class HomeViewModel(application: Application) :BasePageViewModel<DataX>(application) {

    init {
        refresh()
    }

    override fun requestData(page: Int) {
        NetworkPortal.getService(HomeService::class.java)?.getHomeArticles2(page)?.enqueue(
            LiveDataCallback<HomeArticlesResp>(baseLiveData)
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    handleItemData(page, response.data.datas)
                })
    }

    override fun getItemLayoutId(): Int {
        return R.layout.item_home_article_list
    }
}