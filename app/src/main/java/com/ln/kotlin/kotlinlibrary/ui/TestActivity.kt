package com.ln.kotlin.kotlinlibrary.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.mylibrary.BaseActivity
import com.ln.kotlin.mylibrary.widgets.LoadingStateLayout
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_test.*
import java.util.concurrent.TimeUnit

@Route(path = "/ui/TestActivity")
class TestActivity : BaseActivity(),LoadingStateLayout.OnRetryLoadListener {
    override fun onRetry() {
        stateLayout.setViewType(LoadingStateLayout.LOADING_SUCCESS)
    }

    override fun createLayout(): Int {
        return R.layout.activity_test
    }

    override fun initView(savedInstanceState: Bundle?) {

        Observable.interval(5, 1, TimeUnit.SECONDS)
                .compose(toTransformer())
                .subscribe({
                    stateLayout.setViewType(LoadingStateLayout.LOADING_FAILED)
                })
        stateLayout.setOnRetryListener(this)

    }

}
