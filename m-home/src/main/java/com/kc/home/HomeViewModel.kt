package com.kc.home

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kc.library.base.callback.LiveDataCallback
import com.kc.home.response.DataX
import com.kc.home.response.HomeArticlesResp
import com.kc.library.base.BR
import com.kc.library.base.base.BasePageViewModel
import com.kc.library.base.network.NetworkPortal
import com.kc.library.base.router.RouterActivityPath
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @data on 5/14/21 4:40 PM
 * @auther KC
 * @describe  首页
 */
class HomeViewModel(application: Application) :BasePageViewModel<DataX>(application) {

    var finishLoad = MutableLiveData(false)

    val skeleton = ItemBinding.of<String>(BR.item, R.layout.item_home_article_skeleton).bindExtra(BR.viewModel, this)
    val skeletonItem = ObservableArrayList<DataX>()
    init {
        initSkeleton()
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
                    finishLoad.postValue(true)
                })
    }

    override fun getItemLayoutId(): Int {
        return R.layout.item_home_article_layout
    }

    fun onClickItem(item:DataX){
        ARouter.getInstance().build(RouterActivityPath.WebView.WEBVIEW_ACTIVITY)
            .withString("url",item.link)
            .navigation()
    }

    fun initSkeleton(){
        val json = "[{},{},{},{},{},{},{}]"
        skeletonItem.addAll(Gson().fromJson<ArrayList<DataX>>(json,object :TypeToken<ArrayList<DataX>>(){}.type))
    }
}