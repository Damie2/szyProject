package com.smh.szyproject.test.jetpack.bilibiliJetPack.room5;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * author : smh
 * date   : 2020/9/28 14:34
 * desc   :
 */
//如果有多个，用逗号隔开
@Database(entities ={WorksEntity.class},version = 1,exportSchema = false)
public abstract class WorkDataBase extends RoomDatabase {

    public abstract  WorksDao getWorkDao();


}
