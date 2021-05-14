package com.kc.library.base.livedata;


import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.kc.library.base.loading.LoadingDialog

/**
 * @data on 5/6/21 5:47 PM
 * @auther
 * @describe
 */
class BaseLiveDataObserver(private var liveData: BaseLiveData, private var owner: LifecycleOwner, activity: Activity) {

    constructor(liveData: BaseLiveData, activity: AppCompatActivity) : this(liveData, activity, activity)
    constructor(liveData: BaseLiveData, fragment: Fragment) : this(liveData, fragment, fragment.requireActivity())

    private val loadingDialog by lazy {
        var dialog = LoadingDialog(activity)
        dialog
    }

    init {
        /**
         * 坚挺绑定的FinishActivity的LiveData
         * 当发送事件，就销毁当前Activity的操作
         */
        liveData.finishLiveData.observe(owner, { intValue ->
            activity.setResult(intValue)
            activity.finish()
        })

        /**
         * 用来控制显示加载框
         */
        liveData.showLoading.observe(owner, { cancelabls ->
            loadingDialog.window?.decorView?.tag = cancelabls
            if (cancelabls.size > 0) {
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        })
    }
}