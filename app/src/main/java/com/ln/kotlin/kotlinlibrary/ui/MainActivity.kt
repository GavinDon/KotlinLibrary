package com.ln.kotlin.kotlinlibrary.ui

import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.View
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.kotlinlibrary.ui.fragment.HomeFragment
import com.ln.kotlin.kotlinlibrary.ui.fragment.News_Fragment
import com.ln.kotlin.kotlinlibrary.ui.fragment.PersonalFragment
import com.ln.kotlin.kotlinlibrary.ui.fragment.VideoFragment
import com.ln.kotlin.mylibrary.BaseActivity
import com.ln.kotlin.mylibrary.utils.CommonUtils
import com.ln.kotlin.mylibrary.utils.FragmentControl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_title.view.*

class MainActivity : BaseActivity() {
    override fun initView(savedInstanceState: Bundle?) {
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
        val allTab = hashMapOf(home to 0, music to 1, video to 2, personal to 3)
        //map.forEach {(k,v)} android 暂时不支持lambda 使用的是kotlin 的lambda
        // 循环map 来切换tab 并给tab对应的fragment设置title
        allTab.forEach { (k, v) ->
            k.setOnClickListener {
                fragmentControl.switchFragment(v).show()
//                toolbar.mTitle(v)
            }
            k.setTextColor(CommonUtils.createColorState(this, R.color.light_font, R.color.deep_orange_900))
        }
    }

    override fun createLayout(): Int {
      return  R.layout.activity_main
    }

    //获取fragmentControl 实例
    private val fragmentControl = FragmentControl(R.id.fragment_container, this)

/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) //去除系统标题
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
        val allTab = hashMapOf(home to 0, music to 1, video to 2, personal to 3)
        //map.forEach {(k,v)} android 暂时不支持lambda 使用的是kotlin 的lambda
        // 循环map 来切换tab 并给tab对应的fragment设置title
        allTab.forEach { (k, v) ->
            k.setOnClickListener {
                fragmentControl.switchFragment(v).show()
//                toolbar.mTitle(v)
            }
            k.setTextColor(CommonUtils.createColorState(this, R.color.light_font, R.color.deep_orange_900))
        }
    }*/

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
//        toolbar.mTitle(0)
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

    /**
     * 设置标题
     */
    private fun Toolbar.mTitle(index: Int) {
        supportActionBar
        toolbar.setTitleTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))
        when (index) {
            0 -> {
                toolbar.title = "首页"
                toolbar.visibility = View.VISIBLE
            }
            1 -> {
                toolbar.title = "资讯"
                toolbar.visibility = View.GONE
            }
            2 -> {
                toolbar.title = "视频"
                toolbar.visibility = View.VISIBLE
            }
            3 -> {
                toolbar.title = "个人中心"
                toolbar.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

}
