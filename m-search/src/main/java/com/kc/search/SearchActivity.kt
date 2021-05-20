package com.kc.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.mykotlindemo.utils.toast
import com.kc.library.base.base.BaseApplication
import com.kc.library.base.base.BaseMvvmActivity
import com.kc.library.base.router.RouterActivityPath
import com.kc.library.base.utils.ParamViewModelFactory
import com.kc.search.databinding.ActivitySearchBinding
import com.kc.search.response.Data
import java.util.*

@Route(path = RouterActivityPath.Search.SEARCH_ACTIVITY)
class SearchActivity : BaseMvvmActivity<ActivitySearchBinding, SearchViewModel>() {

    @Autowired
    @JvmField
    var searchType = 0 //搜索类型，1-首页、2-公众号文章、3-广场、4-项目

    @Autowired
    @JvmField
    var wxId = 0

    //搜索关键字
    var key = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this) //放在super之前
        super.onCreate(savedInstanceState)
        //不能在BaseMvvmActivity中使用onLoad()方法创建带参数的ViewModel对象，因为这样ARouter永远得不到传过来的值。
        //究其原因，BaseMvvmFragment中的onLoad方法是在CreatViewModel之前执行，而BaseMvvmActivity中的onLoad()方法是在之后。
        dataBinding.ivBack.setOnClickListener { finish() }
        doSearch()
        doClean()
        doFlowLayout()
    }

    //当用户输入框为空，不显示清除按钮；不空，则显示清除按钮并且实现点击清除输入框内容
    private fun doClean() {
        dataBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString() == "") {
                    dataBinding.ivClear.visibility = View.GONE
                } else {
                    key = s.toString()  //赋值给全局变量key，用于创建ViewModel
                    dataBinding.ivClear.visibility = View.VISIBLE
                    //点击清空内容
                    dataBinding.ivClear.setOnClickListener { dataBinding.etSearch.setText("") }
                }
            }
        })
    }

    //点击软键盘搜索按钮执行搜索操作
    private fun doSearch() {
        dataBinding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.refresh()
                return@OnEditorActionListener true
            }
            false
        })
        dataBinding.tvSearch.setOnClickListener {
            viewModel.refresh()
        }
    }

    private fun doFlowLayout() {
        viewModel.itemsHotKeys.observe(this, { list ->
            list?.forEach {
                //建立布局
                val view = LayoutInflater.from(applicationContext).inflate(
                    R.layout.flow_tv_content,
                    null
                )
                val tvContent = view.findViewById<TextView>(R.id.flow_tv)
                tvContent!!.text = it.name
                dataBinding.flowLayout.addView(view)
                //点击事件
                tvContent.setOnClickListener {
                    dataBinding.etSearch.setText(tvContent.text)
                    viewModel.refresh()
                    toast("搜索该热词")
                }
            }
        })
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        //创建含参数的ViewModel
        return ParamViewModelFactory(BaseApplication.instance, searchType, wxId, key)
    }
}