package com.kc.project.service

import com.kc.project.response.ProjectDetail
import com.kc.project.response.ProjectTree
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @data on 5/18/21 1:41 PM
 * @auther KC
 * @describe
 */
interface ProjectService {

    @GET(ProjectApi.PROJECT_TREE)
    fun getProjectTree(): Call<ProjectTree>

    @GET(ProjectApi.PROJECT_LIST)
    fun getProjectList(@Path("page") pageNo:Int,@Query("cid") cid:Int): Call<ProjectDetail>
}