package com.living.test;

import android.test.AndroidTestCase;

import org.junit.Test;


/**
 * Author：燕青 $ on 2016/6/13  17:57
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class TestDemo extends AndroidTestCase {

    @Test
    public void testSuper() {
        Child child = new Child();
        child.say();
    }


    public void testString() {

    }
}
