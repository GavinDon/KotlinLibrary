package com.ln.kotlin.kotlinlibrary.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * description:
 * Created by liNan on 2017/7/7 10:59

 */
class HomeAdapter(internal  val st:String) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onBindViewHolder(holder: HomeViewHolder?, position: Int) {
        TODO("not implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeViewHolder {
        TODO("not implemented")
    }

    override fun getItemCount(): Int {
        TODO("not implemented")
    }
    fun addHeadView(){

    }
    fun addFooterView(){

    }


    inner class HomeViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {


    }

}