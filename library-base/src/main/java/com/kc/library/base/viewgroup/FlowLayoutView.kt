package com.kc.library.base.viewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * @data on 2020/9/8 2:13 PM
 * @auther ArmStrong
 * @describe  流式布局 -- 自定义ViewGroup.  from libo
 */
class FlowLayoutView : ViewGroup {
    private val rows = ArrayList<Row?>()
    private var usedWidth = 0

    /**
     * 当前需要操作的行
     */
    private var curRow: Row? = null
    private val verticalPadding = 30
    private val horizontalPadding = 40

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        restoreLine() //每次重新布局，属性要初始化，避免onMeasure重复调用混乱问题

        //子view设置宽高为父view大小减去padding值
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        //设置每个子view宽高，并且将每个子View归到自己的行
        for (i in 0 until childCount) {
            val childView = getChildAt(i)

            //设置子view设置AT_MOST模式，即布局属性为wrap_content
            val childWidthSpec = MeasureSpec.makeMeasureSpec(
                width,
                if (widthMode == MeasureSpec.EXACTLY) MeasureSpec.AT_MOST else widthMode
            )
            val childHeightSpec = MeasureSpec.makeMeasureSpec(
                height,
                if (heightMode == MeasureSpec.EXACTLY) MeasureSpec.AT_MOST else heightMode
            )
            childView.measure(childWidthSpec, childHeightSpec)
            if (curRow == null) {
                curRow = Row()
            }

            //根据当前childview宽度和剩余宽度判断是否能放进当前行，放不了就要换行
            if (childView.measuredWidth + horizontalPadding > width - usedWidth) {
                //先换行，再放入
                nextLine()
            }
            usedWidth += childView.measuredWidth + horizontalPadding
            curRow!!.addView(childView)
        }

        //将最后一个row加入到rows中
        rows.add(curRow)

        //根据子view组成的高度重设自己高度
        var finalHeight = 0
        for (row in rows) {
            finalHeight += row!!.height + verticalPadding
        }
        setMeasuredDimension(width, finalHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var top = 0
        //遍历每一行，将每一行子view布局
        for (row in rows) {
            row!!.layout(top)
            top += row.height + verticalPadding
        }
    }

    /**
     * 换行，需要将当前row存储，并且创建新的row，新的行使用空间置0
     */
    private fun nextLine() {
        rows.add(curRow)
        curRow = Row()
        usedWidth = 0
    }

    /**
     * 每次onmeasure需要重置信息
     */
    private fun restoreLine() {
        rows.clear()
        curRow = Row()
        usedWidth = 0
    }

    /**
     * 用于记录每一行放置子View的信息
     */
    internal inner class Row {
        /**
         * 该行放置的子view
         */
        private val childViews: MutableList<View> = ArrayList()
        var height = 0
        fun addView(view: View) {
            childViews.add(view)
            height = Math.max(view.measuredHeight, height) //高度取最高子view的高度
        }

        val size: Int
            get() = childViews.size

        /**
         * 将当前childViews进行布局
         * top 当前hang处于的顶部高度
         */
        fun layout(top: Int) {
            var leftMargin = 0
            for (i in childViews.indices) {
                val view = childViews[i]
                view.layout(
                    leftMargin,
                    top,
                    leftMargin + view.measuredWidth,
                    top + view.measuredHeight
                )
                leftMargin += view.measuredWidth + horizontalPadding
            }
        }
    }
}