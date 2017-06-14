package com.ln.kotlin.kotlinlibrary.ui

import android.app.Application
import kotlin.properties.Delegates

/**
 * description: 程序入口
 * Created by liNan on 2017/6/14 10:08

 */
open class App :Application(){
    //创建一个单例
    companion object{
        var instance:App by Delegates.notNull()  //使用委托来检查是否为Null
    }

    init {
        println("init")
    }

    override fun onCreate() {
        super.onCreate()
        println("onCreate")
        instance=this
    }


}