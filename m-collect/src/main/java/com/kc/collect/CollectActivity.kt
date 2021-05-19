package com.kc.collect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.library.base.router.RouterActivityPath

@Route(path = RouterActivityPath.Collect.COLLECT_ACTIVITY)
class CollectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
    }
}