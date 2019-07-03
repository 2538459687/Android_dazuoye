package com.jacky.wanandroidkotlin.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders

/**
 * @author:Hzj
 * @date  :2018/10/30/030
 * desc  ：ViewModel封装基类Activity
 * record：
 */
abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity() {

    protected lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)
        startObserve()
    }

    abstract fun startObserve()

    //初始化ViewModel绑定
    private fun initViewModel() {
        provideViewModelClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
            mViewModel.let(lifecycle::addObserver)
        }
    }

    abstract fun provideViewModelClass(): Class<VM>?

    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }
}