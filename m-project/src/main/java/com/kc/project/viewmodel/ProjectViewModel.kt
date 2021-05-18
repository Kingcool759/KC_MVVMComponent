package com.kc.project.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kc.library.base.base.BaseViewModel
import com.kc.library.base.callback.LiveDataCallback
import com.kc.library.base.network.NetworkPortal
import com.kc.project.service.ProjectService
import com.kc.project.response.Project
import com.kc.project.response.ProjectTree

/**
 * @data on 5/18/21 1:37 PM
 * @auther KC
 * @describe
 */
class ProjectViewModel(application: Application) : BaseViewModel(application) {

    val items = MutableLiveData<List<Project>>()

    fun getProjectList(){
        NetworkPortal.getService(ProjectService::class.java)?.getProjectTree()?.enqueue(
            LiveDataCallback<ProjectTree>(baseLiveData)
                .doOnResponseSuccess { _, response ->
                    items.postValue(response.data)
                })
    }

}