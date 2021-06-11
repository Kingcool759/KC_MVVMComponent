package com.kc.library.base.picker

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.blankj.utilcode.util.KeyboardUtils

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/1/10
 */
object BindingAdapter {


    @JvmStatic
    @BindingAdapter(
        value = ["exOptionSelectorData", "exOptionSelectorListener"],
        requireAll = false
    )
    fun optionSelector(view: View, info: OptionData, listener: OnOptionsSelectListener) {
        val picker =
            OptionPickerStyle.style<String>(
                view.context,
                OptionsPickerBuilder(
                    view.context,
                    listener
                )
            )

        view.setOnClickListener {
            if (info.option1.isEmpty()) {
                return@setOnClickListener
            }
            when {
                info.option2.isEmpty() -> {
                    picker.setPicker(info.option1)
                }
                info.option3.isEmpty() -> {
                    picker.setPicker(info.option1, info.option2)
                }
                else -> {
                    picker.setPicker(info.option1, info.option2, info.option3)
                }
            }
            KeyboardUtils.hideSoftInput(it)
            picker.show(view)
        }

    }


    @JvmStatic
    @BindingAdapter(
        value = ["exOptionSelectorData", "exOptionSelectorListener", "exOptionSelectorPreShowListener"],
        requireAll = false
    )
    fun optionSelector(
        view: View,
        info: OptionData,
        listener: OnOptionsSelectListener,
        clickPreListener: Runnable?
    ) {
        val picker =
            OptionPickerStyle.style<String>(
                view.context,
                OptionsPickerBuilder(
                    view.context,
                    listener
                )
            )

        view.setOnClickListener {
            if (info.option1.isEmpty()) {
                return@setOnClickListener
            }
            clickPreListener?.run()
            when {
                info.option2.isEmpty() -> {
                    picker.setPicker(info.option1)
                }
                info.option3.isEmpty() -> {
                    picker.setPicker(info.option1, info.option2)
                }
                else -> {
                    picker.setPicker(info.option1, info.option2, info.option3)
                }
            }
            KeyboardUtils.hideSoftInput(it)
            picker.show(view)
        }

    }

    @JvmStatic
    @BindingAdapter(
        value = ["exOptionSelectorData", "exOptionSelectorListener"],
        requireAll = false
    )
    fun optionSelector(view: TextView, info: OptionData, listener: OnOptionsSelectListener) {
        val picker =
            OptionPickerStyle.style<String>(
                view.context,
                OptionsPickerBuilder(
                    view.context,
                    listener
                )
            )

        view.setOnClickListener {
            if (info.option1.isEmpty()) {
                return@setOnClickListener
            }
            when {
                info.option2.isEmpty() -> {
                    picker.setPicker(info.option1)
                }
                info.option3.isEmpty() -> {
                    picker.setPicker(info.option1, info.option2)
                }
                else -> {
                    picker.setPicker(info.option1, info.option2, info.option3)
                }
            }
            KeyboardUtils.hideSoftInput(it)
            picker.show(view)
        }

    }

    class OptionData {
        val option1 = ArrayList<String>()
        val option2 = ArrayList<List<String>>()
        val option3 = ArrayList<List<List<String>>>()
    }
}