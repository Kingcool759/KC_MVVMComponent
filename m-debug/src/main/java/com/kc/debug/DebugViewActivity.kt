package com.kc.debug

import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.debug.databinding.ActivityDebugViewBinding
import com.kc.library.base.base.BaseMvvmActivity
import com.kc.library.base.router.RouterActivityPath

@Route(path = RouterActivityPath.Debug.DEBUG_VIEW_ACTIVITY)
class DebugViewActivity : BaseMvvmActivity<ActivityDebugViewBinding,DebugViewModel>() {}