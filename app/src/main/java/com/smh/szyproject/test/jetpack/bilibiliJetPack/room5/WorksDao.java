package com.smh.szyproject.test.jetpack.bilibiliJetPack.room5;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


/**
 * author : smh
 * date   : 2020/9/28 14:28
 * desc   :
 */
@Dao
public interface WorksDao {

    @Insert
    void insert(WorksEntity... worksEntities);//括号里表示可以添加多个参数

    @Update
    void update(WorksEntity... worksEntities);

    @Delete
    void delete(WorksEntity... worksEntities);

    @Query("SELECT * FROM Works")
    List<WorksEntity> getWorks();//这个是获得表里所有数据

    @Query("DELETE from Works")
    void deleteAll();

    //不同的数据集可以不同的形式来返回，这里需要Paging的时候，就可以用DataSource的形式来返回
    @Query("SELECT * FROM Works")
    DataSource.Factory<Integer,WorksEntity> getWorksForPaging();


}
