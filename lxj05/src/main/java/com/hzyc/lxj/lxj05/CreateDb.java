package com.hzyc.lxj.lxj05;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2017/8/25 0025.
 */
public class CreateDb extends SQLiteOpenHelper {
    private static final int VERSION = 1; //版本号
    private static final String DB_NAME = "data.db";
    public CreateDb(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean bol = false;
        db.execSQL("create table pop (id integer primary key autoincrement,name varchar(10),phone varchar(15))");
        bol = true;
        Log.i("数据库动态","创建状态="+bol);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
