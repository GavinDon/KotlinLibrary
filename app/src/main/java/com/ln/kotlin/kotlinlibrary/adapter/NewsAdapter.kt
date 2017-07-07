package com.ln.kotlin.kotlinlibrary.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.kotlinlibrary.bean.NewsBean
import com.ln.kotlin.kotlinlibrary.ui.WebViewActivity
import kotlinx.android.synthetic.main.adapter_news.view.*

/**
 * description: 资讯列表适配器
 * Created by liNan on 2017/7/4 15:40

 */
class NewsAdapter<T : NewsBean>(internal var newsBean: T) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        mContext = parent?.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.adapter_news, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemView!!.run {
            ada_tv_title.text = newsBean.result.data[position].title
            ada_iv.loadImg(newsBean.result.data[position].thumbnail_pic_s)
            ada_tv_from.text = newsBean.result.data[position].author_name
            ada_tv_introduce.text = newsBean.result.data[position].title
            tvTime.text = newsBean.result.data[position].date
            setOnClickListener {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("webView", newsBean.result.data[position].url)
                mContext?.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return newsBean.result?.data?.size ?: 0
    }

    fun updateData(data: T) {
        newsBean = data
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    fun ImageView.loadImg(url: String) {
        Glide.with(mContext).load(url).into(this)
    }


}