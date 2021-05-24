package com.kc.my

import android.app.Application
import android.content.ComponentName
import android.content.Intent
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.library.base.base.BaseViewModel
import com.kc.library.base.router.RouterActivityPath
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @data on 5/18/21 3:57 PM
 * @auther KC
 * @describe
 */
class MineViewModel(application: Application) : BaseViewModel(application) {
    val items = ObservableArrayList<String>()
    val itemBinding = ItemBinding.of<String>(BR.item,R.layout.item_mine)
        .bindExtra(BR.viewModel,this)

    init {
        items.add("我的收藏")
        items.add("我的文章")
        items.add("我的Java系列")
        items.add("我的Kotlin系列")
        items.add("我的DiyView系列")
        items.add("我的试炼场")
        items.add("其他")
    }

    val goToJavaDemoLiveData = MutableLiveData(false)
    val goToKotlinDemoLiveData = MutableLiveData(false)
    val goToDiyDemoLiveData = MutableLiveData(false)

    fun onClickItem(item:String){
        when(item){
            "我的收藏"->{
                ARouter.getInstance().build(RouterActivityPath.Collect.COLLECT_ACTIVITY).navigation()
            }
            "我的文章"->{}
            "我的Java系列"->{
                goToJavaDemoLiveData.postValue(true)
            }
            "我的Kotlin系列"->{
                goToKotlinDemoLiveData.postValue(true)
            }
            "我的DiyView系列"->{
                goToDiyDemoLiveData.postValue(true)
            }
            "我的试炼场"->{}
            "其他"->{}
        }
    }
}