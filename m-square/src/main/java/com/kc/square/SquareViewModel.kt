package com.kc.square

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kc.library.base.BR
import com.kc.library.base.base.BasePageViewModel
import com.kc.library.base.callback.LiveDataCallback
import com.kc.library.base.network.NetworkPortal
import com.kc.library.base.router.RouterActivityPath
import com.kc.square.response.DataX
import com.kc.square.response.SquareArticles
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @data on 5/18/21 12:24 PM
 * @auther KC
 * @describe
 */
class SquareViewModel(application: Application) :BasePageViewModel<DataX>(application) {
    var finishLoad = MutableLiveData(false)

    val skeleton = ItemBinding.of<String>(BR.item, R.layout.item_square_article_skeleton).bindExtra(BR.viewModel, this)
    val skeletonItem = ObservableArrayList<DataX>()
    init {
        initSkeleton()
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
                    finishLoad.postValue(true)
                })
    }

    override fun getItemLayoutId(): Int {
        return R.layout.item_square_article_layout
    }

    fun onItemClick(item:DataX){
        ARouter.getInstance().build(RouterActivityPath.WebView.WEBVIEW_ACTIVITY)
            .withString("url",item.link)
            .navigation()
    }

    fun initSkeleton(){
        val json = "[{},{},{},{},{},{},{}]"
        skeletonItem.addAll(Gson().fromJson<ArrayList<DataX>>(json,object :
            TypeToken<ArrayList<DataX>>(){}.type))
    }
}