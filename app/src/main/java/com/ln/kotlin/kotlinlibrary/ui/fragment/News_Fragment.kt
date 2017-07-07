package com.ln.kotlin.kotlinlibrary.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.kotlinlibrary.adapter.TabLayoutAdapter
import com.ln.kotlin.mylibrary.BaseFragment
import kotlinx.android.synthetic.main.frament_music.*

/**
 * description: 新闻页面
 * Created by liNan on 2017/6/19 15:36

 */
class News_Fragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.frament_music, null) as View
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTab()
    }

    private fun initTab() {
        var fragmentList = mutableListOf<Fragment>()
        val newType = arrayListOf("toutiao", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang")
        (0..newType.size - 1).mapTo(fragmentList) { NewsTabFragments.instance(newType[it]) }
        tabLayout.setupWithViewPager(mViewPager)
        mViewPager.run {
            adapter = TabLayoutAdapter(childFragmentManager, fragmentList)
            currentItem = 0
        }
    }


}