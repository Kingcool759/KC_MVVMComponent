package com.kc.m_webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.library.base.base.BaseActivity
import com.kc.library.base.router.RouterActivityPath
import com.kc.m_webview.databinding.ActivityWebViewBinding

@Route(path = RouterActivityPath.WebView.WEBVIEW_ACTIVITY)
class WebViewActivity : BaseActivity() {

    override fun isImmersionEnable(): Boolean = false

    @Autowired
    @JvmField
    var url = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        val vBinding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(vBinding.root)

        vBinding.webView.settings.javaScriptEnabled = true
        vBinding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                //显示进度条
                vBinding.progressBar.progress = newProgress
                if (newProgress == 100) {
                    //加载完毕隐藏进度条
                    vBinding.progressBar.visibility = View.GONE
                }
                super.onProgressChanged(view, newProgress)
            }
        }
        vBinding.webView.loadUrl(url)
    }
}