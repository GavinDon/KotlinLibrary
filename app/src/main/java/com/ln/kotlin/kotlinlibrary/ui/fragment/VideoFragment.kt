package com.ln.kotlin.kotlinlibrary.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.mylibrary.BaseFragment
import org.jetbrains.anko.bundleOf
import kotlin.properties.Delegates

class VideoFragment : BaseFragment() {
    /**
     * description:视频页
     * Created by liNan on 2017/6/16 17:33

     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
         val view= LayoutInflater.from(activity).inflate(R.layout.frament_video, null)
            return view
    }

    /**
     * 对fragment 传值
     */
    companion object {
        fun newInstance(tag: String): VideoFragment {
            val instance: VideoFragment by Delegates.notNull()
            val args = bundleOf(Pair("type", tag))
            instance.arguments = args
            return instance
        }
    }
}