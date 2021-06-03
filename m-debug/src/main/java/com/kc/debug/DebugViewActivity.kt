package com.kc.debug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.library.base.router.RouterActivityPath

@Route(path = RouterActivityPath.Debug.DEBUG_VIEW_ACTIVITY)
class DebugViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug_view)
    }
}