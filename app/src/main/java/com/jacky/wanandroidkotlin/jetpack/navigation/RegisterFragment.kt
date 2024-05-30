package com.jacky.wanandroidkotlin.jetpack.navigation

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：
 * record：
 */
import com.jacky.wanandroidkotlin.R
import com.jacky.wanandroidkotlin.base.BaseVMFragment
import com.jacky.wanandroidkotlin.databinding.FragmentRegisterBinding
import com.jacky.wanandroidkotlin.ui.login.LoginViewModel


class RegisterFragment : BaseVMFragment<FragmentRegisterBinding,LoginViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_register

    override fun initWidget() {

    }

    override val startObserve: LoginViewModel.() -> Unit = {

    }

}