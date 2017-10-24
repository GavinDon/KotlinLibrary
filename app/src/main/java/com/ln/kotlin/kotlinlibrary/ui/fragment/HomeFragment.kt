package com.ln.kotlin.kotlinlibrary.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.mylibrary.BaseFragment
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * description: 首页fragment
 * Created by liNan on 2017/6/16 17:00

 */
class HomeFragment : BaseFragment() {
    private val bannerList: ArrayList<String> by lazy { arrayListOf("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg", "http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg", "http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg") }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater?.let { inflater.inflate(R.layout.fragment_home, null) } as View
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        banner.setImages(bannerList).setImageLoader(GlideLoader()).start()

    }

    inner class GlideLoader : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            Glide.with(this@HomeFragment).load(path).into(imageView)
        }

    }


}