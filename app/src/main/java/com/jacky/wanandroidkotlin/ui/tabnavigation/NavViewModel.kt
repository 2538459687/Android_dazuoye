package com.jacky.wanandroidkotlin.ui.tabnavigation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jacky.wanandroidkotlin.base.BaseViewModel
import com.jacky.wanandroidkotlin.base.executeRequest
import com.jacky.wanandroidkotlin.model.entity.NavigationEntity
import com.jacky.wanandroidkotlin.model.repositry.NavRepository

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：
 * record：
 */
class NavViewModel(application: Application) : BaseViewModel(application) {
    private val mRepository by lazy { NavRepository() }
    val mNavList: MutableLiveData<MutableList<NavigationEntity>> = MutableLiveData()

    fun getNavigation() {
        executeRequest(showLoading = false,
            request = { mRepository.getNavigationList() },
            onNext = { ok, data, msg ->
                if (ok) {
                    mNavList.value = data
                }
            })
    }

}