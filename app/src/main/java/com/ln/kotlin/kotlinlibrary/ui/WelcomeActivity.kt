package com.ln.kotlin.kotlinlibrary.ui

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ln.kotlin.kotlinlibrary.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.concurrent.TimeUnit


class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        playVideo()
        welcomeVideo.setOnCompletionListener({ playVideo()}) //循环播放
        Observable.interval(3000,TimeUnit.MILLISECONDS)
                .subscribe( {
                    //todo 进入首页
                })

    }

    private fun playVideo() {
        welcomeVideo.setVideoURI(Uri.parse("android.resource://"+packageName+"/"+R.raw.hh))
        welcomeVideo.start()
    }
}
