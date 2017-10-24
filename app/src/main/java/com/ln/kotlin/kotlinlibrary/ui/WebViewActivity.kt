package com.ln.kotlin.kotlinlibrary.ui

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.webkit.*
import com.ln.kotlin.kotlinlibrary.R
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.app_title.*
import org.jetbrains.anko.toast


class WebViewActivity : AppCompatActivity() {
    private lateinit var webUrl: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_web_view)
        toolbar.visibility = View.VISIBLE
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.mipmap.back)
        webUrl = intent.getStringExtra("webView")
        webUrl?.let { loadding.visibility = View.GONE }
        buildWebView()
    }

    private fun buildWebView() {
        val webViewSettings = mWebView.settings
        mWebView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webViewSettings.run {
            javaScriptEnabled = true //允许使用js
            loadsImagesAutomatically = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT // //设置自动加载图片
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = false
            setSupportMultipleWindows(false)
            allowFileAccess = false //禁止使用file
            useWideViewPort = true
            loadWithOverviewMode = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            useWideViewPort = true
            // //适配手机大小
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
            loadWithOverviewMode = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
        }
        mWebView.run {
            webChromeClient = ChromeClient() //进度条
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    if (!webViewSettings.loadsImagesAutomatically) {
                        webViewSettings.loadsImagesAutomatically = true
                    }
                }

                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    view?.loadUrl(webUrl)
                    return true
                }
            }
        }
        if (webUrl.isNullOrEmpty()) toast("url 为 空") else mWebView.loadUrl(webUrl)

    }

    /**
     * 监听返回按钮的点击事件
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId.equals(android.R.id.home) && mWebView.canGoBack()) {
            mWebView.goBack()
        } else if (item!!.itemId.equals(android.R.id.home) && webUrl.equals(toolbar.title) || getString(R.string.app_name).equals(toolbar.title)) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    inner class ChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            when (mProgressBar.progress) {
                100 -> mProgressBar.visibility = View.GONE
                else -> {
                    mProgressBar.progress = newProgress
                    mProgressBar.postInvalidate()
                }
            }
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            toolbar.title = title

        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack() // goBack()表示返回WebView的上一页面
            this.finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView?.clearHistory()
        mWebView?.destroy()
    }

}
