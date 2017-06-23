package com.ln.kotlin.kotlinlibrary.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.mylibrary.BaseFragment
import okhttp3.OkHttpClient
import org.jetbrains.anko.bundleOf
import kotlin.properties.Delegates

/**
 * description:
 * Created by liNan on 2017/6/16 17:00

 */
class HomeFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater?.let { inflater.inflate(R.layout.fragment_home, null) } as View
        initView()
        return view

    }

    private fun initView() {
        dsl()
    }

    /**
     * 对fragment 传值
     */
    companion object {
        fun newInstance(tag: String): HomeFragment {
            val instance: HomeFragment by Delegates.notNull()
            val args = bundleOf(Pair("type", tag))
            instance.arguments = args
            return instance
        }
    }

    private fun dsl() {
        l@ for (i in 0..20) {
            if (i == 2) {
                println(i)
                break@l
            }
        }
    }

}