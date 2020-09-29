package com.smh.szyproject.test.jetpack.bilibiliJetPack.room5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * author : smh
 * date   : 2020/9/28 14:25
 * desc   :
 */
@Entity(tableName = "Works")
public class WorksEntity {

    @PrimaryKey(autoGenerate = true)//作为主键，让它自增加
    private int id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="works")
    private String works;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public WorksEntity(String name, String works) {
        this.name = name;
        this.works = works;
    }
}
