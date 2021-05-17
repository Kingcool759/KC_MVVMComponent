package com.kc.home

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.home.databinding.FragmentHomeBinding
import com.kc.library.base.base.BaseMvvMFragment
import com.kc.library.base.router.RouterFragmentPath

@Route(path = RouterFragmentPath.Home.HOME_FRAGMENT)
class HomeFragment : BaseMvvMFragment<FragmentHomeBinding,HomeViewModel>() {

}