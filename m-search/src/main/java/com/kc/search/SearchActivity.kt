package com.kc.search

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
    var WxId = 0

    @Autowired
    @JvmField
    var pageNo = 0

    @Autowired
    @JvmField
    var key = ""

    override fun onLoad(viewModel: SearchViewModel) {
        ARouter.getInstance().inject(this)
        super.onLoad(viewModel)
        viewModel.getHotKey()
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
                viewModel.getHistoryArticles()
                return@OnEditorActionListener true
            }
            false
        })
        dataBinding.tvSearch.setOnClickListener {
            viewModel.getHistoryArticles()
        }
    }

    private fun doFlowLayout(){
        viewModel.itemsHotKeys.observe(this, { list->
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
                viewModel.getHistoryArticles()
                    toast("搜索该热词")
                }
            }
        })
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        //创建含参数的ViewModel
        when(searchType){
            0 -> {
                toast("传递搜索类型值错误！")
            }
            1 -> {
                toast("首页文章搜索")
            }
            2 -> {
                toast("公众号文章搜索")
                return ParamViewModelFactory(BaseApplication.instance, WxId, pageNo, key)
            }
            3 -> {
                toast("广场文章搜索")
            }
            4 -> {
                toast("项目文章搜索")
            }
        }
        return ParamViewModelFactory(BaseApplication.instance, WxId, pageNo, key)  //默认搜索公众号文章
    }
}