package com.ln.kotlin.kotlinlibrary.widget

import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.View
import android.widget.VideoView

/**
 * description:
 * Created by liNan on 2017/6/14 16:19

 */
class WelcomeVideo : VideoView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 由于手机大小不同 解决不能占满全屏的问题
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width= View.getDefaultSize(0,widthMeasureSpec)
        val height= View.getDefaultSize(0,heightMeasureSpec)
        setMeasuredDimension(width,height)
    }

    override fun setOnPreparedListener(l: MediaPlayer.OnPreparedListener?) {
        super.setOnPreparedListener(l)
    }

}