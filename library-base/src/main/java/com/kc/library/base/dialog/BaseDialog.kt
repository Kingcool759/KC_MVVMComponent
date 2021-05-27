package com.kc.library.base.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import kotlin.math.roundToInt

/**
 * @data on 5/27/21 12:05 PM
 * @auther
 * @describe
 */
open class BaseDialog : Dialog {
    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener)

    /**
     * 设置Dialog的宽度
     */
    fun setDialogWidth(width: Int) {
        window?.let {
            var widthAttributes = it.attributes
            widthAttributes.width = width
            it.attributes = widthAttributes
        }
    }

    /**
     * 根据百分比设置宽度
     */
    fun setDialogWidthPercent(widthPercent: Float) {
        setDialogWidth(((widthPercent * getDialogWidth()).roundToInt()))
    }

    /**
     * 获取到dialog的宽度
     */
    fun getDialogWidth(): Int {
        return context.resources.displayMetrics.widthPixels
    }


    /**
     * 设置Dialog的高度
     */
    fun setDialogHeight(height: Int) {
        window?.let {
            var heightAttributes = it.attributes
            heightAttributes.height = height
            it.attributes = heightAttributes
        }
    }

    fun setDialogHeightPercent(heightPercent: Float) {
        setDialogHeight(((heightPercent * getDialogHeight()).roundToInt()))
    }

    fun getDialogHeight(): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**
     * 设置当前dialog所处的位置
     */
    fun setDialogGravity(dialogGravity: Int) {
        window?.setGravity(dialogGravity)
    }

    /**
     * 设置
     */
    fun setDialogInAndOutAnimation(animationRes: Int) {
        window?.setWindowAnimations(animationRes)
    }

    /**
     * 设置背景
     */
    fun setDialogBackground(bgDraw: Drawable?) {
        window?.decorView?.background = bgDraw
    }

    fun setDialogBackground(bgRes: Int) {
        window?.decorView?.setBackgroundResource(bgRes)
    }

    fun setDialogBackgroundColor(bgColor: Int) {
        window?.decorView?.setBackgroundColor(bgColor)
    }
}