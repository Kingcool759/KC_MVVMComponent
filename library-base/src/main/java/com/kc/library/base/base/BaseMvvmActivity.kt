package com.kc.library.base.base;

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.kc.library.base.BR
import com.kc.library.base.ext.getVClass
import com.kc.library.base.ext.getVmClass
import com.kc.library.base.livedata.BaseLiveDataObserver

/**
 * @data on 5/6/21 5:35 PM
 * @auther KC
 * @describe
 */
open class BaseMvvmActivity<V: ViewDataBinding,VM: BaseViewModel> : BaseActivity() {
    lateinit var dataBinding: V
    lateinit var viewModel: VM
    lateinit var mBaseLiveDataObserver: BaseLiveDataObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDataBinding()
        viewModel = createViewModel()
        dataBinding.setVariable(getVariableId(), viewModel)
        viewModel.setActivity(this)
        // 这句话的作用 是在xml中使用了LiveData，能监听到LiveData数据源发生变化
        dataBinding.lifecycleOwner = this
        mBaseLiveDataObserver = viewModel.baseLiveData.attach(this, this)
        onLoad(viewModel)
    }

    /**
     * 创建DataBinding
     */
    private fun createDataBinding() {
        var vClass = getVClass<V>(this)
        var method = vClass.getMethod("inflate", LayoutInflater::class.java)
        dataBinding = method.invoke(null, layoutInflater) as V
        setContentView(dataBinding.root)
    }

    /**
     * 创建ViewModel
     */
    private fun createViewModel(): VM {
        var vmClass = getVmClass<VM>(this)
        return getViewModelFactory()?.let {
            ViewModelProvider(this, it).get(vmClass)
        } ?: ViewModelProvider(this).get(vmClass)
    }

    /**
     * 如果你想传递参数到ViewModel中
     * 可以重写这个方法，通过Factory 重新构造一个带参数的ViewModel
     */
    open fun getViewModelFactory(): ViewModelProvider.Factory? {
        return null
    }

    /**
     * BR.viewModel 是由文件 base_br_layout生成的
     */
    private fun getVariableId() = BR.viewModel


    /**
     * 通过重写此方法获取到ViewModel
     */
    open fun onLoad(viewModel: VM) {

    }
}