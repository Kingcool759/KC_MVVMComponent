package com.kc.my

import android.content.ComponentName
import android.content.Intent
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
        //appbarlayout滑动监听事件
        dataBinding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset < -500) {
                dataBinding.endScrollContentView.visibility = View.VISIBLE
            } else {
                dataBinding.endScrollContentView.visibility = View.INVISIBLE
            }
        })

        viewModel.goToJavaDemoLiveData.observe(this, {
            if (it) {
                //第一种方式：通过包名+指定Activity路径跳转
                val intent = Intent(Intent.ACTION_MAIN)
                val componentName = ComponentName(
                    "com.example.mydemo",
                    "com.example.mydemo.activity.MainActivity"
                )
                intent.component = componentName
                intent.putExtra("", "") //这里通过Intent传值。
                startActivity(intent)
//                第二种方式：通过包名跳转到另一个app的启动页
//                val intent: Intent = this.getPackageManager().getLaunchIntentForPackage("kuyu.com.xxxx")
//                if (intent != null) {
//                    intent.putExtra("type", "110")
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                }
            }
        })

        viewModel.goToKotlinDemoLiveData.observe(this, {
            if (it) {
                val intent = Intent(Intent.ACTION_MAIN)
                val componentName = ComponentName(
                    "com.example.mykotlindemo",
                    "com.example.mykotlindemo.main.MainActivity"
                )
                intent.component = componentName
                intent.putExtra("", "")
                startActivity(intent)
            }
        })

        viewModel.goToDiyDemoLiveData.observe(this, {
            if (it) {
                val intent = Intent(Intent.ACTION_MAIN)
                val componentName = ComponentName(
                    "com.example.diyview",
                    "com.example.diyview.main.MainActivity"
                )
                intent.component = componentName
                intent.putExtra("", "")
                startActivity(intent)
            }
        })
    }
}