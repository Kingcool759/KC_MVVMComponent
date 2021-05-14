package com.kc.library.base.loading


import android.graphics.Color
import com.scwang.smartrefresh.layout.internal.ProgressDrawable

/**
 * @data on 5/6/21 5:55 PM
 * @auther
 * @describe
 */
class WhiteProgressDrawable : ProgressDrawable() {
    init {
        mPaint.color = Color.parseColor("#ffffffff")
    }
}