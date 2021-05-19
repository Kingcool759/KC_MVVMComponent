package com.kc.m_search

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.bar.OnTitleBarListener
import com.kc.library.base.base.BaseMvvmActivity
import com.kc.library.base.router.RouterActivityPath
import com.kc.m_search.databinding.ActivitySearchBinding

@Route(path = RouterActivityPath.Search.SEARCH_ACTIVITY)
class SearchActivity : BaseMvvmActivity<ActivitySearchBinding,SearchViewModel>() {

    override fun onLoad(viewModel: SearchViewModel) {
        super.onLoad(viewModel)
        dataBinding.toolBar.setOnTitleBarListener(object : OnTitleBarListener {
            override fun onLeftClick(v: View) {
                //左边
                finish()
            }

            override fun onTitleClick(v: View) {
                //中间
            }

            override fun onRightClick(v: View) {
                //右边
            }
        })
    }
}