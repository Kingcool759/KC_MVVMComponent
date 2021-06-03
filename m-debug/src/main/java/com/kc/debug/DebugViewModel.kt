package com.kc.debug

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.example.mykotlindemo.utils.toast
import com.kc.library.base.base.BaseViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @data on 6/3/21 11:11 AM
 * @auther
 * @describe
 */
class DebugViewModel(application: Application) : BaseViewModel(application) {
    val items = ObservableArrayList<String>()
    val itemBinding = ItemBinding.of<String>(BR.item,R.layout.debug_item_layout).bindExtra(BR.viewModel,this)
    init {
        items.add( "ItemBinding实现多布局" )
    }
    fun onItemClick(item:String){
        when(item){
            "ItemBinding实现多布局" ->{
                toast("ItemBinding实现多布局")
            }
        }
    }
}