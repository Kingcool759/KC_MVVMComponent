package com.kc.project.response

import com.kc.library.base.response.BaseResponse

/**
 * @data on 5/18/21 1:45 PM
 * @auther
 * @describe
 */
data class ProjectTree(
    val `data`: List<Project>,
):BaseResponse()

data class Project(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)