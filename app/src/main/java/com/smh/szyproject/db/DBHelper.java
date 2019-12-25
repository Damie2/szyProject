package com.smh.szyproject.db;

import android.database.Cursor;
import android.os.Environment;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/18.
 */
public class DBHelper {

    public static final String DB_NAME = "szy.db";
    public static final int DB_VERSION = 11;

    private static DBHelper dbHelper;
    private DbManager.DaoConfig daoConfig;

    public DBHelper() {
        // for test
        daoConfig = new DbManager.DaoConfig()
                .setDbName(DB_NAME)
                .setDbVersion(DB_VERSION)
                .setDbDir(Environment.getExternalStorageDirectory())
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            synchronized (DBHelper.class) {
                if (dbHelper == null) {
                    dbHelper = new DBHelper();
                }
            }
        }
        return dbHelper;
    }

    public void close() {
        try {
            DbManager db = x.getDb(daoConfig);
            if (db != null)
                db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Object object) {
        try {
            DbManager db = x.getDb(daoConfig);
            db.saveOrUpdate(object);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(Class<?> clazz) {
        try {
            DbManager db = x.getDb(daoConfig);
            db.delete(clazz);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void delete(Class<?> clazz, WhereBuilder builder) {
        try {
            DbManager db = x.getDb(daoConfig);
            db.delete(clazz, builder);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Class<?> clazz, Object idValue) {
        try {
            DbManager db = x.getDb(daoConfig);
            db.deleteById(clazz, idValue);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(Class<?> clazz) {
        try {
            DbManager db = x.getDb(daoConfig);
            db.dropTable(clazz);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> findAll(Class<T> t) {
        try {
            DbManager db = x.getDb(daoConfig);
            return db.findAll(t);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T findById(Class<T> t, Object id) {
        try {
            DbManager db = x.getDb(daoConfig);
            return db.findById(t, id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void updateTable(DbManager db, Class<?> tClass) {
        try {
            if (db.getTable(tClass) != null) {
                String tableName = tClass.getSimpleName();
                String sql = "select * from " + tableName;
                Map<String, String> fieldMap = new HashMap<>();
                Cursor cursor = db.execQuery(sql);
                int count = cursor.getColumnCount();
                for (int i = 0; i < count; i++) {
                    fieldMap.put(cursor.getColumnName(i), cursor.getColumnName(i));
                }
                cursor.close();

                Field[] fields = tClass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    if (fieldMap.containsKey(fields[i].getName())) {
                        //假如字段名已存在就进行下次循环
                        continue;
                    } else {
                        //不存在，就放到map中，并且给表添加字段
                        fieldMap.put(fields[i].getName(), fields[i].getName());
                        //根据属性的类型给表增加字段
                        if (fields[i].getType().toString().equals("class java.lang.String")) {
                            db.execNonQuery("alter table " + tableName + " add " + fields[i].getName() + " TEXT ");
                        } else if (fields[i].getType().toString().equals("int")
                                || fields[i].getType().toString().equals("long")
                                || fields[i].getType().toString().equals("boolean")) {
                            db.execNonQuery("alter table " + tableName + " add " + fields[i].getName() + " INTEGER ");
                        }
                    }
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
