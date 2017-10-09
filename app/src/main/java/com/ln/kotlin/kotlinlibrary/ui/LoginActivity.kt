package com.ln.kotlin.kotlinlibrary.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ln.kotlin.kotlinlibrary.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast


@Route(path = "/ui/LoginActivity")
class LoginActivity : AppCompatActivity() {

    @Autowired
    @JvmField var key3: String=""
    @Autowired
    @JvmField var url:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ARouter.getInstance().inject(this)
        toast(key3)
        webview.loadUrl(url)
    }
}
