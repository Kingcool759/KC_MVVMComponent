package com.kc.debug.debugview

import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.debug.databinding.MutiltypeActivityViewBinding
import com.kc.library.base.base.BaseMvvmActivity
import com.kc.library.base.router.RouterActivityPath

@Route(path = RouterActivityPath.Debug.MUTIL_TYPE_ACTIVITY)
class MutilTypeActivity : BaseMvvmActivity<MutiltypeActivityViewBinding, MutilTypeViewModel>() {}