package com.kc.library.base.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @data on 5/18/21 2:05 PM
 * @auther KC
 * @describe
 */

//第一种方法：被companion object伴生对象修饰的代码块为static的。但要加上@JvmStatic。
//class ImageUrlBindingAdapter {
//
//    companion object{
//        @BindingAdapter(value = ["exImageUrl"], requireAll = false)
//        @JvmStatic
//        fun setUrl(imageView: ImageView?, url: String?) {
//            if (url != null) {
//                Glide.with(imageView!!).load(url).into(imageView)
//            }
//        }
//    }
//}
//第而种方法：将class改为object类。但要加上@JvmStatic。
object ImageUrlBindingAdapter {
    @BindingAdapter(value = ["exImageUrl"], requireAll = false)
    @JvmStatic
    fun setUrl(imageView: ImageView?, url: String?) {
        if (url != null) {
            Glide.with(imageView!!).load(url).into(imageView)
        }
    }
}