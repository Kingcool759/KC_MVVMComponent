package com.kc.my

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.AppBarLayout
import com.kc.library.base.base.BaseMvvMFragment
import com.kc.library.base.router.RouterFragmentPath
import com.kc.my.databinding.FragmentMineBinding

@Route(path = RouterFragmentPath.User.MINE_FRAGMENT)
class MineFragment : BaseMvvMFragment<FragmentMineBinding, MineViewModel>() {
    override fun isFitsSystemWindow(): Boolean = false
    override fun onLoad(view: View) {
        super.onLoad(view)
        //滑动监听事件
        dataBinding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset < -500) {
                dataBinding.endScrollContentView.visibility = View.VISIBLE
            } else {
                dataBinding.endScrollContentView.visibility = View.INVISIBLE
            }
        })
    }
}