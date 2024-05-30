package com.jacky.wanandroidkotlin.model.repositry

import com.jacky.wanandroidkotlin.model.api.WanRetrofitClient
import com.jacky.wanandroidkotlin.model.entity.NavigationEntity
import com.jacky.wanandroidkotlin.model.entity.WanResponse

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：
 * record：
 */
class NavRepository {

    /**
     * 获取导航列表
     */
    suspend fun getNavigationList(): WanResponse<MutableList<NavigationEntity>> {
        return WanRetrofitClient.mService.getNavigation()
    }
}