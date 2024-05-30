package com.jacky.wanandroidkotlin.model.entity

import java.io.Serializable

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：首页文章分页获取
 * record：
 */
data class ArticleList(
    val offset: Int,
    val size: Int,
    val total: Int,
    val pageCount: Int,
    val curPage: Int,
    val over: Boolean,
    val datas: MutableList<ArticleEntity>
) : Serializable