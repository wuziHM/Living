package com.living.greendao.util;

import com.living.greendao.service.DbService;
import com.living.greendao.service.UserService;

/**
 *
 * Created by tobin on 2016/5/26.
 */
public class DbUtil {

    private static UserService userService;

    public static DbService getUserService() {
        if (userService == null) {
            userService = new UserService(DbCore.getDaoSession().getUserDao());
        }
        return userService;
    }


}
