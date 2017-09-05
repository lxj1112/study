package com.hzyc.yy.demo_07;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {

    private Button create,add,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        create = (Button) findViewById(R.id.create);
        add = (Button) findViewById(R.id.add);
        search = (Button) findViewById(R.id.search);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDb db = new CreateDb(Main3Activity.this);
                SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDb db = new CreateDb(Main3Activity.this);
                SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
                //添加方法
                sqLiteDatabase.execSQL("insert into person (name) values ('张三')");
                sqLiteDatabase.execSQL("insert into person (name) values ('李四')");
                sqLiteDatabase.execSQL("insert into person (name) values ('王五')");
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDb db = new CreateDb(Main3Activity.this);
                SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();

                Cursor cursor = sqLiteDatabase.rawQuery("select * from person order by id",null);
                //curso游标

                while(cursor.moveToNext()){
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));

                    Log.i("数据库动态","编号="+id);
                    Log.i("数据库动态","名称="+name);
                }
            }
        });
    }
}
