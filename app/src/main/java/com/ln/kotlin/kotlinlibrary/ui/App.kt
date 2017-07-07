package com.ln.kotlin.kotlinlibrary.ui

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import cat.ereza.customactivityoncrash.config.CaocConfig
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


    override fun onCreate() {
        super.onCreate()
        instance=this
        CaocConfig.Builder.create()
//                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
//                .enabled(false) //default: true
                .showErrorDetails(false) //default: true
//                .showRestartButton(false) //default: true
//                .trackActivities(true) //default: false
//                .minTimeBetweenCrashesMs(2000) //default: 3000
//                .errorDrawable(R.mipmap.home_press) //default: bug image
                .restartActivity(MainActivity::class.java) //default: null (your app's launch activity)
                .errorActivity(CustomErrorActivity::class.java) //default: null (default error activity)
                .eventListener(CustomEventListener())
                .apply()
    }

    private class CustomEventListener : CustomActivityOnCrash.EventListener {
        override fun onLaunchErrorActivity() {
            Log.i(TAG, "onLaunchErrorActivity()")
        }

        override fun onRestartAppFromErrorActivity() {
            Log.i(TAG, "onRestartAppFromErrorActivity()")
        }

        override fun onCloseAppFromErrorActivity() {
            Log.i(TAG, "onCloseAppFromErrorActivity()")
        }
    }


}