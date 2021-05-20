package com.kc.wx

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.library.base.base.BaseApplication
import com.kc.library.base.base.BaseMvvMFragment
import com.kc.library.base.router.RouterFragmentPath
import com.kc.library.base.utils.ParamViewModelFactory
import com.kc.wx.databinding.FragmentWxCountsBinding
import com.kc.wx.viewmodel.WxAccountViewModel

@Route(path = RouterFragmentPath.Wx.WX_ACCOUNTS)
class WxCountsFragment : BaseMvvMFragment<FragmentWxCountsBinding, WxAccountViewModel>() {

    @JvmField
    @Autowired
    var account_id = 0

    override fun onLoad(view: View) {
        /**
         * 关于ARouter与super的位置关系
         * arouter接收数据必须放在super之前，否则viewModel创建时无参数值，
         * 因为ViewModel和View绑定，如果没有拿到数据创建的viewModel获取不到数据，返回的View为空，页面显示为空数据。
         */
        ARouter.getInstance().inject(this)   //super之前调用
        super.onLoad(view)
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        //创建含参数的ViewModel
        return ParamViewModelFactory(BaseApplication.instance,account_id)
    }
}