package com.ln.kotlin.kotlinlibrary.ui

import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.kotlinlibrary.ui.fragment.HomeFragment
import com.ln.kotlin.kotlinlibrary.ui.fragment.News_Fragment
import com.ln.kotlin.kotlinlibrary.ui.fragment.PersonalFragment
import com.ln.kotlin.kotlinlibrary.ui.fragment.VideoFragment
import com.ln.kotlin.mylibrary.BaseActivity
import com.ln.kotlin.mylibrary.utils.CommonUtils
import com.ln.kotlin.mylibrary.utils.FragmentControl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    //获取fragmentControl 实例
    private val fragmentControl = FragmentControl(R.id.fragment_container, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            addFragment()
        } else {
            val home: String = HomeFragment::class.java.simpleName
            val news: String = News_Fragment::class.java.simpleName
            val video: String = VideoFragment::class.java.simpleName
            val personal: String = PersonalFragment::class.java.simpleName
            //为了把fragment 重新加入到集合中
            fragmentControl.findWithByTag(true, arrayListOf(home, news, video, personal))
        }
        home.setOnClickListener {
            fragmentControl.switchFragment(0).show()
        }
        music.setOnClickListener {
            fragmentControl.switchFragment(1).show()
        }
        video.setOnClickListener {
            fragmentControl.switchFragment(2).show()
        }
        personal.setOnClickListener {
            fragmentControl.switchFragment(3).show()
        }

        home.setTextColor(CommonUtils.createColorState(this, R.color.light_font, R.color.deep_orange_900))
        personal.setTextColor(CommonUtils.createColorState(this, R.color.light_font, R.color.deep_orange_900))
        video.setTextColor(CommonUtils.createColorState(this, R.color.light_font, R.color.deep_orange_900))
        music.setTextColor(CommonUtils.createColorState(this, R.color.light_font, R.color.deep_orange_900))

    }

    /**
     * 给activity 添加fragment 并默认显示首页 使用add-hide-show 的方式
     */
    private fun addFragment() {
        fragmentControl.addFragment(HomeFragment(),
                News_Fragment(),
                VideoFragment(),
                PersonalFragment())
        //默认显示首页
        fragmentControl.switchFragment(0).show()


    }

    /**
     * 动态改变selector
     */
    fun createDrawable(normalRes: Int, pressRes: Int): StateListDrawable {
        val normalDrawable = ContextCompat.getDrawable(this, normalRes)
        val pressDrawable = ContextCompat.getDrawable(this, pressRes)
        val stateList = StateListDrawable()
        stateList.addState(intArrayOf(android.R.attr.state_pressed, android.R.attr.state_checked), pressDrawable)
        stateList.addState(intArrayOf(), normalDrawable)
        return stateList
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }


}
