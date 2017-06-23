package com.ln.kotlin.kotlinlibrary.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.mylibrary.BaseFragment
import org.jetbrains.anko.bundleOf
import kotlin.properties.Delegates

/**
 * description:
 * Created by liNan on 2017/6/19 15:41

 */
class PersonalFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.frament_personal, null) as View
        return view
    }
    /**
     * 对fragment 传值
     */
    companion object {
        fun newInstance(tag: String): PersonalFragment {
            val instance: PersonalFragment by Delegates.notNull()
            val args = bundleOf(Pair("type", tag))
            instance.arguments = args
            return instance
        }
    }
}