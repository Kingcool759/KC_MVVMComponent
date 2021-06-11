package com.kc.debug.tabscroll

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.example.mykotlindemo.utils.toast
import com.kc.debug.BR
import com.kc.debug.R
import com.kc.library.base.base.BaseViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @author
 * @email
 * @date 2021-06-11
 */
class TabViewModel(application: Application) : BaseViewModel(application) {

    var items = ObservableArrayList<Item>()
//    var itemBinding = ItemBinding.of<Item>(BR.item, R.layout.scroll_tab_body)
//        .bindExtra(BR.viewModel, this@TabViewModel)
    /**
     * 第三种方式：
     * 通过注入方式把position传递进布局，声明时，通过：
     *  <variable
    name="position"
    type="Integer" />方式来用
     */
    var itemBinding = ItemBinding.of<Item> { itemBinding, position, item ->
        itemBinding.bindExtra(BR.position, position)
            .set(BR.item, R.layout.scroll_tab_body)
            .bindExtra(BR.viewModel, this@TabViewModel)
    }
    var tabs = ObservableArrayList<Item>()

    init {
        loadTab()
        loadData()
    }

    fun loadTab() {
        for (i in 1..10) {
            tabs.add(Item())
        }
        tabs.forEachIndexed { index, item ->
            if (index == 0) {
                item.name.value = "全部"
            } else {
                item.name.value = "Tab$index"
            }
        }
    }

    fun loadData() {
        for (i in 1..10) {
            items.add(Item())
        }
        items.forEachIndexed { index, item ->
            val position = index + 1
            item.name.value = "Item$position"
        }
    }

    fun onItemClick(item: Item, pos: Int) {
        //第一种方式
//        val position = items.indexOf(item)
        toast("当前Item位置：$pos")  //第三种方式
    }
}