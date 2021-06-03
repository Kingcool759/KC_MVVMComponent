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

    @Autowired
    @JvmField
    var url = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        val vBinding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(vBinding.root)

        /**
         * 预加载
         * 预加载会导致这么两个问题：
         * 第一个：因为把webView的初始化放在了application中，所以开启app进程的时候会进行初始化，明显启动速度下降；
         * 第二个；因为全局静态对象复用的一个，所以当打开第二个webView路径的时候，明显会发现第一个页面还会有短暂的出现，
         * 之后再变更成第二个url对应的网页，用户体验并不好……
         */
//        vBinding.webViewContainer.addView(webView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
//        webView.settings.javaScriptEnabled = true
//        webView.webChromeClient = object : WebChromeClient() {
//            override fun onProgressChanged(view: WebView?, newProgress: Int) {
//                //显示进度条
//                vBinding.progressBar.progress = newProgress
//                if (newProgress == 100) {
//                    //加载完毕隐藏进度条
//                    vBinding.progressBar.visibility = View.GONE
//                }
//                super.onProgressChanged(view, newProgress)
//            }
//        }
//        webView.loadUrl(url)

        /**
         * 非预加载
         */
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

//    override fun onDestroy() {
//        //懒加载需要
//        (webView.parent as? ViewGroup)?.let {
//            it.removeView(webView)
//        }
//        super.onDestroy()
//    }
}