package com.kc.m_search

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.library.base.base.BaseMvvmActivity
import com.kc.library.base.router.RouterActivityPath
import com.kc.m_search.databinding.ActivitySearchBinding

@Route(path = RouterActivityPath.Search.SEARCH_ACTIVITY)
class SearchActivity : BaseMvvmActivity<ActivitySearchBinding, SearchViewModel>() {

    override fun onLoad(viewModel: SearchViewModel) {
        super.onLoad(viewModel)
        dataBinding.ivBack.setOnClickListener { finish() }
        doSearch()
        doClean()
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
                    dataBinding.ivClear.setOnClickListener{ dataBinding.etSearch.setText("") }
                }
            }
        })
    }

    //点击软键盘搜索按钮执行搜索操作
    private fun doSearch() {
        dataBinding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Toast.makeText(baseContext, "执行搜索操作", Toast.LENGTH_SHORT).show()
                return@OnEditorActionListener true
            }
            false
        })
    }
}