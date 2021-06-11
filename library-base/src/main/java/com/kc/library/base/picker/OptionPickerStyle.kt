package com.kc.library.base.picker

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.view.OptionsPickerView
import com.kc.library.base.R
import com.kc.library.base.utils.getActivity

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/1/10
 */
object OptionPickerStyle {
    fun <T> style(context: Context, builder: OptionsPickerBuilder): OptionsPickerView<T> {
        var picker: OptionsPickerView<T>? = null
        picker = builder
            .apply {
                setCancelColor(ContextCompat.getColor(context, R.color.font_gray))
                setLayoutRes(R.layout.base_pickerview_options) {
                    it.findViewById<View>(R.id.cancel).setOnClickListener {
                        picker?.dismiss()
                    }
                    it.findViewById<View>(R.id.ok).setOnClickListener {
                        picker?.returnData()
                        picker?.dismiss()
                    }
                }
                setLineSpacingMultiplier(2.6f)
                setTextColorCenter(ContextCompat.getColor(context, R.color.font_orange))
                setContentTextSize(16)
                setDividerColor(ContextCompat.getColor(context, R.color.divider))
                context.getActivity()?.let {
                    setDecorView(it.window.decorView.findViewById(android.R.id.content))
                }
            }
            .build()
        return picker
    }

}