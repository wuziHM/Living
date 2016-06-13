package com.living;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.living.greendao.model.User;
import com.living.greendao.util.DbUtil;
import com.living.test.Child;
import com.living.util.LogUtil;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testMap() {
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map1.put("key" + i, "value" + i);
        }
        map2.put("xxxx", "map2d的value");
        map1.putAll(map2);
        Iterator<Map.Entry<String, String>> it = map1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            Log.e("Logu", "key:" + next.getKey() + "    value:" + next.getValue());
        }
    }


    @Test
    public void testGreenDao() throws Exception {
//        UserService userService = DbUtil.getUserService();
        User user=new User();
        user.setAccount("张三");
        user.setHeight(168);

        DbUtil.getUserService().save(user);

        List<User> test = DbUtil.getUserService().queryAll();

        if (test != null && test.size()>0){
            LogUtil.e("User: " + test.get(0).toString() + " //getAccount: " + test.get(0).getAccount());
        }

    }

}