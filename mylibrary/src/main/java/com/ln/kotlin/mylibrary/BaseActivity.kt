package com.ln.kotlin.mylibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window


/**
 * description:baseActivity
 * Created by liNan on 2017/6/14 9:42

 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) //去除系统标题
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

}