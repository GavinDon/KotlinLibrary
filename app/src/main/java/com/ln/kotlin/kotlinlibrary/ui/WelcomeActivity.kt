package com.ln.kotlin.kotlinlibrary.ui

import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.mylibrary.BaseActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.intentFor
import java.util.concurrent.TimeUnit


class WelcomeActivity : BaseActivity() {
    private var countTime: Int = 0
    //val 可以使用lazy 懒加载  var可以用lateInit 用不能为基本类型
    val txCountTime: AppCompatTextView by lazy {
        findViewById(R.id.timeOut) as AppCompatTextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        playVideo()
        welcomeVideo.setOnCompletionListener({ playVideo() }) //循环播放
        countDown(4)

    }

    private fun playVideo() {
        welcomeVideo.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.hh))
        welcomeVideo.start()
    }

    /**
     * 倒计时time S 可以点击进入 主界面
     */
    private fun countDown(time: Int) {
        countTime = time
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { countTime - it } //转换数据 1，2，3为3，2，1
                .take((countTime + 1).toLong()) //countTime+1s后结束计时
                .subscribe({
                    val s = "$it s进入"
                    if (0L == it) {
                        txCountTime.text = "进入"
                        txCountTime.setOnClickListener {
                            startActivity(intentFor<MainActivity>())
                            this.finish()
                        }
                    } else {
                        txCountTime.text = s
                    }

                }

                )

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
