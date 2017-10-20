package com.ln.kotlin.mylibrary.widgets

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.ln.kotlin.mylibrary.R


/**
 * description: 加载状态管理页面
 * Created by liNan on 2017/10/19 15:29

 */
class LoadingStateLayout : FrameLayout, View.OnClickListener {
    private lateinit var failView: View
    private lateinit var netErrorView: View
    private lateinit var emptyView: View
    private lateinit var loadingView: View

    override fun onClick(v: View?) {
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        initView()
    }

    companion object {
        val LOADING = 0      //加载中
        val LOADING_SUCCESS = 1   //加载成功
        val LOADING_FAILED = 2  // 加载失败
        val LOADING_EMPTY = 3  //加载成功 数据为空
        val LOADING_NET_ERROR = 4 //网络出错
    }

    private var loadType: Int = -1 //加载状态标志

    /**
     * 创建布局
     * @param layoutRes 加载布局ID
     */
    private fun createViewById(layoutRes: Int): View {
        //inflate 内部已经attach过了所以此处传值为false
        val view = LayoutInflater.from(context).inflate(layoutRes, this, false)
        return view
    }

    /**
     * 移除所有子view
     */
    private fun removeChildView() {
        for (i in 0 until childCount) {
            removeViewAt(i)
        }
    }

    /**
     * 设置加载页面的类型
     */
    fun setViewType(type: Int) {
        loadType = type
        visibility = View.VISIBLE
        when (loadType) {
            LOADING -> Log.i("s", "LOADING")
            LOADING_SUCCESS -> {
                removeChildView()
            }
            LOADING_FAILED -> {
                removeChildView()
                failView = createViewById(R.layout.loading_fail)
                this.addView(failView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
                setOnClickListener {}
            }
            LOADING_EMPTY -> {
            }
            LOADING_NET_ERROR -> {
                removeChildView()
                netErrorView = createViewById(R.layout.load_net_error)
                this.addView(netErrorView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
                setOnClickListener { retryListener?.onRetry() }
            }
        }
    }

    interface OnRetryLoadListener {
        fun onRetry()
    }

    private var retryListener: OnRetryLoadListener? = null
    fun setOnRetryListener(listener: OnRetryLoadListener) {
        retryListener = listener
    }

}