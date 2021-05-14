package com.kc.library.base.loading

import android.content.Context
import android.os.Bundle
import android.view.Window
import com.kc.library.base.R
import com.kc.library.base.databinding.BaseLoadingDialogLayoutBinding

/**
 * @data on 5/6/21 5:48 PM
 * @auther
 * @describe
 */
class LoadingDialog: BaseDialog {
    constructor(context: Context) : super(context, R.style.base_loadingDialogTheme)

    val vBinding by lazy {
        BaseLoadingDialogLayoutBinding.inflate(layoutInflater)
    }

    init {
        setContentView(vBinding.root)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ////设置加载框的样式
        vBinding.loadingDialogPb.indeterminateDrawable = WhiteProgressDrawable()
    }

    override fun getWindow(): Window? {
        return super.getWindow()!!
    }
}