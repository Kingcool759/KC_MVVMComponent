package com.kc.debug.moveconfict

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.kc.debug.BR
import com.kc.debug.R
import com.kc.library.base.base.BaseViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @data on 2021/8/3 10:36 上午
 * @auther
 * @describe
 */
class MoveConfictViewModel(application: Application) :BaseViewModel(application){
    val items = ObservableArrayList<String>()
    val itemBinding = ItemBinding.of<String>(BR.item,R.layout.confict_recycler_layout)
        .bindExtra(BR.viewModel,this)

    init {
        for (i in 1..20){
            items.add("ssss$i")
        }
    }
}