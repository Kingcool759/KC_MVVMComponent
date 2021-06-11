package com.kc.debug.pickerdate

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.debug.databinding.DateActivityPickerBinding
import com.kc.library.base.base.BaseMvvmActivity
import com.kc.library.base.router.RouterActivityPath
import com.kc.library.base.utils.DateUtil

/**
 * @author 
 * @email 
 * @date 2021-06-11
 */
@Route(path = RouterActivityPath.Debug.PICKER_DATE_ACTIVITY)
class PickerActivity : BaseMvvmActivity<DateActivityPickerBinding, PickerViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val timePickerPlus = TimePickerPlus(this)
        timePickerPlus.okListener = { date ->
            val dateToString = DateUtil.dateToString(date, "yyyy-MM-dd")
            dataBinding.tvDate.text = dateToString
        }

        dataBinding.btnShowTimePicker.setOnClickListener {
            if (!timePickerPlus.isShowing) {
                timePickerPlus.show()
            }
        }
    }
}