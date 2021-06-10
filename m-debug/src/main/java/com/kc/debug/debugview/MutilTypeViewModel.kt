package com.kc.debug.debugview.mutiltype

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.example.mykotlindemo.utils.toast
import com.kc.debug.BR
import com.kc.debug.R
import com.kc.debug.debugview.Data
import com.kc.debug.debugview.User
import com.kc.library.base.base.BaseViewModel
import com.kc.library.base.callback.LiveDataCallback
import com.kc.library.base.network.NetworkPortal
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

/**
 * @data on 6/3/21 1:43 PM
 * @auther
 * @describe
 */
class MutilTypeViewModel(application: Application) : BaseViewModel(application) {
    var items = MergeObservableList<Any>()
    val itemBody = ObservableArrayList<Data>()
    var itemBinding: OnItemBind<Any> = OnItemBindClass<Any>()
        .map(User::class.java) { itemBinding, position, item ->
            itemBinding.set(BR.item, R.layout.main_item_header)
                .bindExtra(BR.viewModel, this@MutilTypeViewModel)
        }.map(Data::class.java) { itemBinding, position, item ->
            itemBinding.set(BR.item, R.layout.main_item_body)
                .bindExtra(BR.viewModel, this@MutilTypeViewModel)
        }.map(String::class.java) { itemBinding, position, item ->
            itemBinding.set(BR.item, R.layout.main_item_footer)
                .bindExtra(BR.viewModel, this@MutilTypeViewModel)
        }

    init {
        items.insertItem(User())
        items.insertList(itemBody)
        items.insertItem("")
        itemBody.add(Data())
    }

    fun addItem() {
        val max = 3
        if (itemBody.size >= max) {
            toast("最多添加${max}个商品")
            return
        }
        itemBody.add(Data())
    }
}
