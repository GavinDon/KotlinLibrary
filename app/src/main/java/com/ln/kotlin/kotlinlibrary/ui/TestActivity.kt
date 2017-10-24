package com.ln.kotlin.kotlinlibrary.ui

import android.os.Bundle
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.mylibrary.BaseActivity
import com.ln.kotlin.mylibrary.widgets.LoadingStateLayout
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_test.*
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

class TestActivity : BaseActivity() {

    override fun createLayout(): Int {
        return R.layout.activity_test
    }

    override fun initView(savedInstanceState: Bundle?) {

        Observable.interval(5, 1, TimeUnit.SECONDS)
                .compose(toTransformer())
                .subscribe({
                    stateLayout.setViewType(LoadingStateLayout.LOADING_FAILED)
                })
        stateLayout.setOnRetryListener(object : LoadingStateLayout.OnRetryLoadListener {
            override fun onRetry() {
                toast("afdf")
            }
        })

    }

}
