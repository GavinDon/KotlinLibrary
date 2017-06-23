package com.ln.kotlin.mylibrary.utils

import android.content.Context
import android.content.res.ColorStateList
import android.support.v4.content.ContextCompat

/**
 * description:
 * Created by liNan on 2017/6/20 8:51

 */
class CommonUtils {
    companion object{
        /**
         * 按下时字体颜色的变化
         */
        fun createColorState(context: Context,normalColor:Int,pressColor:Int): ColorStateList {
            val state= arrayOf(intArrayOf(android.R.attr.state_selected), intArrayOf())
            val colorArray= intArrayOf(ContextCompat.getColor(context,pressColor),ContextCompat.getColor(context,normalColor))
            return ColorStateList(state,colorArray)
        }

    }


}