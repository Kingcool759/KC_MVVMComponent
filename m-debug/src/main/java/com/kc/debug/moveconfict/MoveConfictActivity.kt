package com.kc.debug.moveconfict

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.debug.databinding.ActivityMoveConfictBinding
import com.kc.library.base.base.BaseMvvmActivity
import com.kc.library.base.router.RouterActivityPath

@Route(path = RouterActivityPath.Debug.MOVE_CONFICT_ACTIVITY)
class MoveConfictActivity : BaseMvvmActivity<ActivityMoveConfictBinding,MoveConfictViewModel>() {

    val layoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        layoutManager.orientation = RecyclerView.HORIZONTAL
//        dataBinding.recycler.layoutManager = layoutManager
    }
}