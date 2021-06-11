package com.kc.debug

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.alibaba.android.arouter.launcher.ARouter
import com.example.mykotlindemo.utils.toast
import com.kc.library.base.base.BaseViewModel
import com.kc.library.base.router.RouterActivityPath
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @data on 6/3/21 11:11 AM
 * @auther
 * @describe
 */
class DebugViewModel(application: Application) : BaseViewModel(application) {
    val items = ObservableArrayList<String>()
    val itemBinding =
        ItemBinding.of<String>(BR.item, R.layout.debug_item_layout).bindExtra(BR.viewModel, this)

    init {
        items.add("ItemBinding实现多布局")
        items.add("Tab+Recycler相互定位")
        items.add("Picker选择日期")
    }

    fun onItemClick(item: String) {
        when (item) {
            "ItemBinding实现多布局" -> {
                ARouter.getInstance().build(RouterActivityPath.Debug.MUTIL_TYPE_ACTIVITY)
                    .navigation()
            }
            "Tab+Recycler相互定位" ->{
                ARouter.getInstance().build(RouterActivityPath.Debug.TAB_SCOLL_ACTIVITY)
                    .navigation()
            }
            "Picker选择日期" ->{
                ARouter.getInstance().build(RouterActivityPath.Debug.PICKER_DATE_ACTIVITY)
                    .navigation()
            }
        }
    }
}