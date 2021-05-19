package com.kc.home

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.library.base.callback.LiveDataCallback
import com.kc.home.response.DataX
import com.kc.home.response.HomeArticlesResp
import com.kc.library.base.base.BasePageViewModel
import com.kc.library.base.network.NetworkPortal
import com.kc.library.base.router.RouterActivityPath
import com.kc.library.base.router.RouterFragmentPath

/**
 * @data on 5/14/21 4:40 PM
 * @auther KC
 * @describe  首页
 */
class HomeViewModel(application: Application) :BasePageViewModel<DataX>(application) {

    init {
        refresh()
    }

    override fun requestData(page: Int) {
        NetworkPortal.getService(HomeService::class.java)?.getHomeArticles2(page)?.enqueue(
            LiveDataCallback<HomeArticlesResp>(baseLiveData)
                .bindLoading()
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    handleItemData(page, response.data.datas)
                })
    }

    override fun getItemLayoutId(): Int {
        return R.layout.item_home_article_list
    }

    fun onClickItem(item:DataX){
        ARouter.getInstance().build(RouterActivityPath.WebView.WEBVIEW_ACTIVITY)
            .withString("url",item.link)
            .navigation()
    }
}