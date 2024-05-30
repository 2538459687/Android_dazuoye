package com.jacky.wanandroidkotlin;

import org.junit.Test;

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：
 * record：
 */
public class TryCatchTest {

    @Test
    public void test() {
        String s = fun1();
        System.out.println("return:" + s);
    }

    private String fun1() {
        try {
            throw new IllegalStateException("我抛异常了");
        } catch (Exception e) {
            System.out.println("catch:" + e.getMessage());
        } finally {
            System.out.println("finally:");
        }
        return "666";
    }
}
