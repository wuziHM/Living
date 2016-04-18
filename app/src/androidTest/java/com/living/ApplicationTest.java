package com.living;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
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
}