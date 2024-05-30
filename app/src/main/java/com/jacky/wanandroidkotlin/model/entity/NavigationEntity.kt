package com.jacky.wanandroidkotlin.model.entity

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：导航数据实体
 * record：
 */
data class NavigationEntity(val articles: List<ArticleEntity>,
                            val cid: Int,
                            val name: String)