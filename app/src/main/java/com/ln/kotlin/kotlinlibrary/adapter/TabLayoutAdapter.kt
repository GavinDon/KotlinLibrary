package com.ln.kotlin.kotlinlibrary.adapter

import android.app.Fragment
import android.app.FragmentManager
import android.support.v13.app.FragmentPagerAdapter


/**使用app包下的fragment 适配器就得使用v13的。。
 * description:tabLayout 适配器
 * Created by liNan on 2017/4/11 14:30
 */

class TabLayoutAdapter(fm: FragmentManager, internal var fragmentLst: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int):Fragment {
        return fragmentLst[position]
    }

    override fun getCount(): Int {
        return fragmentLst.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return title[position]
    }

    companion object {
        private val title = arrayOf("头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚")
    }
}
