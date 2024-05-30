package com.jacky.wanandroidkotlin.wrapper.musicplay

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：播放过程回调接口
 * record：
 */
interface IPlayerStatusListener {
    /**
     * 缓冲更新
     */
    fun onBufferingUpdate(percent:Int)

    /**
     * 播放完成
     */
    fun onPlayComplete()
}