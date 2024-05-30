package com.jacky.wanandroidkotlin.model.repositry

import com.jacky.wanandroidkotlin.model.api.WanRetrofitClient
import com.jacky.wanandroidkotlin.model.entity.ArticleList
import com.jacky.wanandroidkotlin.model.entity.WanResponse

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：我的收藏文章列表
 * record：
 */
class MyCollectRepository : CollectRepository() {

    /**
     * 获取我的收藏文章列表
     */
    suspend fun getMyCollectArticleList(pageNum: Int): WanResponse<ArticleList> {
        return WanRetrofitClient.mService.getMyCollectArticleList(pageNum)
    }

}