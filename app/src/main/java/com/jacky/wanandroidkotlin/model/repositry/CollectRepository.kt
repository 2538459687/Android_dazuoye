package com.jacky.wanandroidkotlin.model.repositry

import com.jacky.wanandroidkotlin.model.api.WanRetrofitClient
import com.jacky.wanandroidkotlin.model.entity.ArticleList
import com.jacky.wanandroidkotlin.model.entity.WanResponse

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：收藏，取消收藏文章
 * record：
 */
open class CollectRepository {

    /**
     * 收藏文章
     */
    suspend fun collectArticle(articleId: Int): WanResponse<ArticleList> {
        return WanRetrofitClient.mService.collectArticle(articleId)
    }

    /**
     * 取消收藏文章
     */
    suspend fun unCollectArticle(articleId: Int): WanResponse<ArticleList> {
        return WanRetrofitClient.mService.cancelCollectArticle(articleId)
    }
}