package com.ln.kotlin.mylibrary

import android.app.Fragment
import android.os.Bundle


/**
 * description: baseFragment
 * 记录一下：fragment 与fragmentActivity 的区别
 * fragmentActivity是为了兼容3.0以前继承Activity实现的类
 * Android 3.0之后就可以直接继承自Activity，并且在其中嵌入使用Fragment
 *
 * 每个fragment 各自保存自己的状态
 * Created by liNan on 2017/6/16 16:59

 */
abstract class BaseFragment : Fragment() {
    object final {
        internal const val STATE_SAVE_IS_HIDDEN: String = "STATE_SAVE_IS_HIDDEN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            val isSupportHidden = savedInstanceState.getBoolean(final.STATE_SAVE_IS_HIDDEN)
            val ft = fragmentManager.beginTransaction()
            if (isSupportHidden) ft.hide(this) else ft.show(this)
            ft.commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(final.STATE_SAVE_IS_HIDDEN, isHidden)
    }



}