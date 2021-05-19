package com.kc.home

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.mykotlindemo.utils.toast
import com.hjq.bar.OnTitleBarListener
import com.kc.home.databinding.FragmentHomeBinding
import com.kc.library.base.base.BaseMvvMFragment
import com.kc.library.base.router.RouterActivityPath
import com.kc.library.base.router.RouterFragmentPath

@Route(path = RouterFragmentPath.Home.HOME_FRAGMENT)
class HomeFragment : BaseMvvMFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun onLoad(view: View) {
        super.onLoad(view)
        dataBinding.toolBar.setOnTitleBarListener(object : OnTitleBarListener {
            override fun onLeftClick(v: View) {
                //左边
//                activity?.finish()
            }

            override fun onTitleClick(v: View) {
                //中间
            }

            override fun onRightClick(v: View) {
                //右边
                ARouter.getInstance().build(RouterActivityPath.Search.SEARCH_ACTIVITY).navigation()
            }
        })
    }
}