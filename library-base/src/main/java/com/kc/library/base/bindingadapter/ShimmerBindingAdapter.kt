package com.kc.library.base.bindingadapter

import androidx.databinding.BindingAdapter
import io.supercharge.shimmerlayout.ShimmerLayout

/**
 * @data on 5/25/21 10:22 AM
 * @auther KC
 * @describe 给RecyclerView的item布局增加id指定java代码的方式，骨架闪动效果实现。
 */
object ShimmerBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["startShimmerAnimation"],requireAll = false)
    fun startAnimation(shimmerLayout: ShimmerLayout,state:Boolean){
        if (state){
            shimmerLayout.startShimmerAnimation()
        }
    }
}