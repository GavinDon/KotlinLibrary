package com.ln.kotlin.kotlinlibrary.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

/**
 * description:  使用贝塞尔曲线绘制header
 * Created by liNan on 2017/10/16 10:28

 */
class BezierHeader : View {
    private val arcPath: Path by lazy { Path() } //孤形的画笔
    private val mPaint: Paint by lazy { Paint() } //孤形的画笔
    private var startPoint: PointF = PointF(0f, 0f)
    private var controlPoint: PointF = PointF(0f, 0f)
    private var endPoint: PointF = PointF(0f, 0f)

    private var mWidth by Delegates.notNull<Int>()
    private var mHeight by Delegates.notNull<Int>()

    private  var arcHeight:Int = 50

    // 左右偏移 φ
    private val fai = 0
    // 上下偏移
    private val k = -50f
    // 角速度
    private val w = 0.5f
    // 振幅
    private val a = 20
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        mPaint.apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            strokeWidth = 5F
            color=Color.parseColor("#f8bbd0")
        }
        fun initPath(){

        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        arcPath.reset()
        mWidth = w
        mHeight = h
        //绘制一层矩形
        val rect = RectF(0f, 0f, mWidth.toFloat(), mHeight.toFloat() - arcHeight)
        arcPath.addRect(rect, Path.Direction.CCW)
        //绘制曲线的三个坐标点
        startPoint.x = 0f
        startPoint.y = mHeight.toFloat() - arcHeight
        controlPoint.x = (mWidth / 2).toFloat()
        controlPoint.y = mHeight.toFloat() + arcHeight
        endPoint.x = mWidth.toFloat()
        endPoint.y = mHeight.toFloat() - arcHeight
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        arcPath.moveTo(startPoint.x, startPoint.y)
        arcPath.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y)
        canvas?.drawPath(arcPath, mPaint)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }
}