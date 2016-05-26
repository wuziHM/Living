package com.living.greendao.service;

import com.living.greendao.dao.UserDao;
import com.living.greendao.model.User;

/**
 *
 *
 * Created by tobin on 2016/5/26.
 */
public class UserService extends DbService<User,Long> {
    public UserService(UserDao dao) {
        super(dao);
    }
}
