package com.hzyc.lxj.lxj05;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button create;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateDb db = new CreateDb(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        create = (Button) findViewById(R.id.create);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                Map<String,Object> map = getList().get(position);
                int id1 = Integer.parseInt(map.get("id").toString());
                String name1 = map.get("name").toString();
                String phone1 = map.get("phone").toString();
                intent.putExtra("id",id1);
                intent.putExtra("name",name1);
                intent.putExtra("phone",phone1);
                startActivity(intent);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        CreateDb db = new CreateDb(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from pop order by id",null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id",id);
            map.put("name", name);
            map.put("phone",phone);
            list.add(map);
        }
        return list;
    }
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return getList().size();
        }

        @Override
        public Object getItem(int position) {
            return getList().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                //获取外部的配置文件
                //LayoutInflater 布局填充器
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.lianxiren, null);
            } else {
                view = convertView;
            }
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView phone = (TextView) view.findViewById(R.id.phone);
            name.setText(getList().get(position).get("name").toString());
            phone.setText(getList().get(position).get("phone").toString());
            return view;
        }
    }
}
