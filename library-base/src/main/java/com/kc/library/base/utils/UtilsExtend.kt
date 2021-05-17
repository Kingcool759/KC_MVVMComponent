package com.example.mykotlindemo.utils

import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.kc.library.base.base.BaseApplication

/**
 * @data on 4/28/21 10:57 AM
 * @auther KC
 * @describe
 */
/**
 * 弹出吐司
 */
fun toast(message: String) {
    val toast = Toast.makeText(BaseApplication.instance, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

/**
 * 打印日志
 */
fun logd(tag:String,message: String){
    Log.d(tag,message)
}