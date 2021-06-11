package com.kc.debug.pickerdate

import android.app.Activity
import android.graphics.Color
import android.view.ViewGroup
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.kc.debug.R
import com.kc.debug.databinding.WidgetTimePickerPlusLayoutBinding
import com.kc.library.base.dialog.BottomDialog
import java.util.*

/**
 * @author : zhangqi
 * @time : 2020/9/24
 * desc : 选择时间的对话框
 */
class TimePickerPlus(activity: Activity) : BottomDialog(activity) {

    val viewBinding by lazy {
        WidgetTimePickerPlusLayoutBinding.inflate(layoutInflater)
    }
    var timePicker: TimePickerView
    lateinit var date: Date

    init {
        //设置布局
//        setContentView(R.layout.widget_time_picker_plus_layout)
        setContentView(viewBinding.root)
        setCanceledOnTouchOutside(false)

        //拿到时间选择器
        timePicker = TimePickerBuilder(activity, OnTimeSelectListener { it, v ->
            date = it
        })
            .setItemVisibleCount(4)
            .setLineSpacingMultiplier(3f).setTimeSelectChangeListener {
                date = it
            }
            .setRangDate(Calendar.getInstance().apply {
                add(Calendar.YEAR, -3)
            }, Calendar.getInstance())
            .setDate(Calendar.getInstance())
            .setContentTextSize(16)
            .setTextColorCenter(context.resources.getColor(R.color.blue))
            .setTextColorOut(context.resources.getColor(R.color.black))
            .setDividerColor(Color.parseColor("#FFDDDDDD"))
            .build()
        timePicker.returnData()
        val timePickerLayoutContainer = timePicker.findViewById(R.id.timepicker)
        (timePickerLayoutContainer.parent as ViewGroup).removeView(timePickerLayoutContainer)
        viewBinding.timePickerContainer.addView(timePickerLayoutContainer)

        //设置点击事件
        //取消的点击事件
        viewBinding.tvCancel.setOnClickListener {
            timePicker.returnData()
            dismiss()
        }

        //确认的点击事件
        viewBinding.tvConfirm.setOnClickListener {
            timePicker.returnData()
            okListener?.invoke(date)
            dismiss()
        }

    }

    /**
     * 确认的事件
     */
    var okListener: ((date: Date) -> Unit)? = null
}