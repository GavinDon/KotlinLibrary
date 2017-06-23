package com.ln.kotlin.mylibrary.utils

import android.content.Context
import android.content.res.ColorStateList
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.ln.kotlin.mylibrary.R

/**
 * description: 动态改变字体颜色
 * Created by liNan on 2017/6/19 15:51

 */
class MyTextView : AppCompatTextView {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.setTextColor(createColorState(R.color.deep_orange_300,R.color.deep_orange_900))
    }

    private fun createColorState(normalColor:Int,pressColor:Int):ColorStateList{
        val state= arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf(-android.R.attr.state_pressed))
        val colorArray= intArrayOf(pressColor,normalColor)
        return ColorStateList(state,colorArray)
    }


}