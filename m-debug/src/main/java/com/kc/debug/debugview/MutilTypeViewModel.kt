package com.kc.debug.debugview

import android.app.Application
import com.example.mykotlindemo.utils.toast
import com.kc.debug.BR
import com.kc.debug.R
import com.kc.debug.picker.BindingAdapter
import com.kc.library.base.base.BaseViewModel
import me.tatarka.bindingcollectionadapter2.OnItemBind
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

/**
 * @data on 6/3/21 1:43 PM
 * @auther
 * @describe
 */
class MutilTypeViewModel(application: Application) : BaseViewModel(application) {
    val clientName = InitLiveData("")
    val clientOption = BindingAdapter.OptionData()
    val requireOption = BindingAdapter.OptionData()
    var items = MergeObservableList<Any>()
    val itemBody = ObservableArrayListPro<Data>()
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
        getRequire()
        getClient()
    }

    fun addItem() {
        val max = 3
        if (itemBody.size >= max) {
            toast("最多添加${max}个商品")
            return
        }
        itemBody.add(Data())
    }

    fun getRequire(){
        requireOption.option1.add("天")
        requireOption.option1.add("周")
        requireOption.option1.add("月")
    }
    fun onRequireItemClick(item: Data, index: Int) {
        item.unit.value = "/"+requireOption.option1[index]
    }

    fun getClient(){
        clientOption.option1.add("长春桥串串店")
        clientOption.option1.add("长春桥牛肉胡辣汤店")
        clientOption.option1.add("长春桥麻辣香锅店")
    }
    fun onClienItemClick(index: Int){
        clientName.value = clientOption.option1[index]
    }
}
