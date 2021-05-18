package com.kc.square

import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.library.base.base.BaseMvvMFragment
import com.kc.library.base.router.RouterFragmentPath
import com.kc.square.databinding.FragmentSquareBinding

@Route(path = RouterFragmentPath.Square.SQUARE_FRAGMENT)
class SquareFragment : BaseMvvMFragment<FragmentSquareBinding,SquareViewModel>() {}