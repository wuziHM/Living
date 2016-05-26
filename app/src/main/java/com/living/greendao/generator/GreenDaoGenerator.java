package com.living.greendao.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 *
 * @author tobin
 */
public class GreenDaoGenerator {
    public static void main(String[] args) throws Exception {
        // 代表创建的数据库的版本号以及默认的java package，如果不修改默认的包名，生成的dao和model都会在该包下
        Schema schema = new Schema(1, "com.living.greendao.model");
        schema.setDefaultJavaPackageDao("com.living.greendao.dao");
        schema.enableKeepSectionsByDefault();

        //实体类是否支持active 如果开启了的话，实体类将之间支持update, refresh, deleted 等操作。
        //schema.enableActiveEntitiesByDefault();

        addEntity(schema);

        //这个是生成Dao文件的路径的位置
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

    private static void addEntity(Schema schema) {
        // Entity表示一个实体可以对应成数据库中的表
        // "User"相当于是表的类名，用User生成对象就可以访问这个表属性了，也就是这个表对应了这个类.
        Entity user = schema.addEntity("User");
        user.setTableName("User");
        user.addIdProperty();
        // 字段参数设置
        user.addStringProperty("account").unique();
        user.addStringProperty("password");
        user.addDateProperty("birthday");
        user.addShortProperty("gender");
        user.addIntProperty("height");
        user.addFloatProperty("weight");
        user.addDateProperty("registerTime");

        // 添加一个身份证表 和User表关联起来

        Entity card = schema.addEntity("Card");
        card.addIdProperty().primaryKey();
        card.addStringProperty("number");
        card.addStringProperty("address");

        // 通过addToOne方法进行映射
        Property cardPK = user.addLongProperty("cardId").getProperty();
        user.addToOne(card, cardPK);

        Property personPK = card.addLongProperty("userId").getProperty();
        card.addToOne(user, personPK);

    }

}
