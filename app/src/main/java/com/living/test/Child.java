package com.living.test;

import com.living.util.LogUtil;

/**
 * Author：燕青 $ on 2016/6/13  17:53
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class Child extends Father {
    @Override
    public void say() {
//        super.say();
        LogUtil.e("I am child");
    }
}
