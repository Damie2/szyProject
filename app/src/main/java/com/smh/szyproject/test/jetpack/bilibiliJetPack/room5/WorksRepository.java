package com.smh.szyproject.test.jetpack.bilibiliJetPack.room5;

import android.content.Context;

import androidx.room.Room;

/**
 * author : smh
 * date   : 2020/9/28 14:38
 * desc   :
 */
public class WorksRepository {
    private static volatile WorksRepository instance;

    private WorkDataBase workDataBase;
    private WorksDao worksDao;


   public WorksRepository(Context context) {
       //数据库的名字叫做works_db
//       workDataBase = Room.databaseBuilder(context,WorkDataBase.class,"works_db").build();
       //为了运行在主线程操作，就添加，平常是不能在主线程操作的，可以在rxjava中进行操作
       workDataBase = Room.databaseBuilder(context,WorkDataBase.class,"works_db").allowMainThreadQueries().build();
       worksDao = workDataBase.getWorkDao();

   }

    public static WorksRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (WorksRepository.class) {
                if (instance == null) {
                    instance = new WorksRepository(context);
                }
            }
        }
        return instance;
    }

    public WorksDao getWorksDao() {
        return worksDao;
    }
}
