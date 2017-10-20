package com.ln.kotlin.mylibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * description:baseActivity
 * Created by liNan on 2017/6/14 9:42

 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) //去除系统标题
//        setContentView(R.layout.base_layout)
//        frameContent.addView(creatView(), ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
//        addContentView(creatView(), ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT))
        setContentView(createLayout())
        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun createLayout(): Int
    fun creatView():View{
       return LayoutInflater.from(this).inflate(createLayout(),null,false)

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * compose
     * @param upstream 是一个Observable
     * @return  ObservableSource
     */
    fun <T> toTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    }
}