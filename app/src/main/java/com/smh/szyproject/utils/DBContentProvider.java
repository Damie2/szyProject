package com.smh.szyproject.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.CallLog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Create by smh on 2019/6/10.
 */
// 1.在ContentProvider.query()中自定义rawQuery和execSQL方法
public class DBContentProvider extends ContentProvider {
    private static SQLiteOpenHelper mHelper;


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        switch(sortOrder){
            case "rawQuery": //查询SQL
                return db.rawQuery(selection, null);
            case "execSQL": //增删改SQL
                db.execSQL(selection);
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public static class MyContentResolver {
        private static final Uri URI = CallLog.Calls.CONTENT_URI;

        public static Cursor rawQuery(Context cxt, String sql) {
            try {
                return cxt.getContentResolver().query(URI, null, sql, null, "rawQuery");
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return null;
        }

        public static void execSQL(Context cxt, String sql) {
            try {
                cxt.getContentResolver().query(URI, null, sql, null, "execSQL");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

}