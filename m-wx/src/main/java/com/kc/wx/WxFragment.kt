package com.kc.wx

import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.library.base.adapter.PublicTabAdapter
import com.kc.library.base.base.BaseMvvMFragment
import com.kc.library.base.router.RouterFragmentPath
import com.kc.wx.databinding.FragmentWxBinding

@Route(path = RouterFragmentPath.Wx.WX_FRAGMENT)
class WxFragment : BaseMvvMFragment<FragmentWxBinding, WxViewModel>() {

    val fragments = ArrayList<Fragment>()
    val titles = ArrayList<String>()

    override fun onLoad(view: View) {
        super.onLoad(view)
        viewModel.getWxCodeList()
        setTabLayout()
    }

    //设置TabLayout和ViewPager
    fun setTabLayout() {
        viewModel.items.observe(this, { item ->
            item.forEach {
                dataBinding.tablayout.addTab(dataBinding.tablayout.newTab().setText(it.name))
                fragments.add(
                    ARouter.getInstance().build(RouterFragmentPath.Wx.WX_ACCOUNTS)
                        .withInt("account_id", it.id)
                        .navigation() as Fragment
                )
                titles.add(it.name)
            }
            dataBinding.viewPager.adapter =
                PublicTabAdapter(childFragmentManager, fragments, titles)
            dataBinding.tablayout.setupWithViewPager(dataBinding.viewPager)
        })
    }
}