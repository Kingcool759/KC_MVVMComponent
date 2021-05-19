package com.kc.my

import android.app.Application
import androidx.databinding.ObservableArrayList
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

    fun onClickItem(item:String){
        when(item){
            "我的收藏"->{
                ARouter.getInstance().build(RouterActivityPath.Collect.COLLECT_ACTIVITY).navigation()
            }
            "我的文章"->{}
            "其他"->{}
        }
    }
}