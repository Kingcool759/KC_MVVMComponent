package com.kc.project.viewmodel

import android.app.Application
import com.kc.library.base.base.BasePageViewModel
import com.kc.library.base.callback.LiveDataCallback
import com.kc.library.base.network.NetworkPortal
import com.kc.project.service.ProjectService
import com.kc.project.R
import com.kc.project.response.DataX
import com.kc.project.response.ProjectDetail

/**
 * @data on 5/18/21 1:39 PM
 * @auther KC
 * @describe
 */
class ProDetailsViewModel(application: Application,var cid:Int) : BasePageViewModel<DataX>(application) {
    init {
        refresh()
    }

    override fun requestData(page: Int) {
        NetworkPortal.getService(ProjectService::class.java)?.getProjectList(page,cid)?.enqueue(
            LiveDataCallback<ProjectDetail>(baseLiveData)
                .bindLoading()
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    handleItemData(page, response.data.datas)
                })

    }

    override fun getItemLayoutId(): Int {
        return R.layout.item_project_layout
    }

    fun onItemClick(item : DataX){

    }
}