package com.ln.kotlin.kotlinlibrary.ui

import android.app.Activity
import android.app.Application
import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.appcompat.BuildConfig
import android.util.Log
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.alibaba.android.arouter.launcher.ARouter
import kotlin.properties.Delegates


/**
 * description: 程序入口
 * Created by liNan on 2017/6/14 10:08

 */
class App : Application(), Application.ActivityLifecycleCallbacks {


    private var mIsBackground = false


    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
        if (mIsBackground) {
            mIsBackground = false
            println("onTrimMemory app从后台进入前台")
            dialog(activity!!)

        }
    }

    override fun onActivityStarted(activity: Activity?) {
        println("onActivityStarted")
    }

    override fun onActivityDestroyed(activity: Activity?) {
        println("onActivityDestroyed")
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        println("onActivitySaveInstanceState")
    }

    override fun onActivityStopped(activity: Activity?) {
        println("onActivityStopped")
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        println("onActivityCreated")
    }

    //创建一个单例
    companion object {
        var instance: App by Delegates.notNull()  //使用委托来检查是否为Null
    }

    /**
     * application下的每个Activity声明周期改变时，都会触发以下的函数。
     */
    //模拟展示广告
    private fun dialog(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("展示广告")
        builder.setTitle("提示")
        builder.setPositiveButton("确认", { dialog, _ -> dialog.dismiss() })
        builder.setNegativeButton("取消", { dialog, _ -> dialog.dismiss() })
        builder.create().show()
    }

    // 程序终止的时候执行
    override fun onTerminate() {
        unregisterActivityLifecycleCallbacks(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            mIsBackground = true
            Log.d(TAG, "onTrimMemory app退出到后台")
        }
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
        registerActivityLifecycleCallbacks(this)
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


    private  class CustomEventListener : CustomActivityOnCrash.EventListener {
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