package com.jacky.wanandroidkotlin.navigation

/**
 * @author:Hzj
 * @date  :2020/7/28
 * desc  ：
 * record：
 */
import android.app.Activity
import com.jacky.wanandroidkotlin.R
import com.jacky.wanandroidkotlin.base.BaseVMActivity
import com.zenchn.support.router.Router


class WelcomeActivity : BaseVMActivity<WelcomeViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_welcome

    override fun initWidget() {

    }

    override val startObserve: WelcomeViewModel.() -> Unit = {

    }

    /**
     * 伴生类:静态单例内部类
     */
    companion object Num {

        fun launch(from: Activity) {
            Router
                .newInstance()
                .from(from)
                .to(WelcomeActivity::class.java)
                .launch()
        }
    }
}