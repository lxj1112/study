package com.hzyc.lxj.lxj01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());
    }
    public List<Map<String,Object>> getList(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 0;i<=16; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("photo",R.drawable.image1);
            map.put("name",R.id.name);
            map.put("price",R.id.price);
            map.put("pingxin",i);
            map.put("beizhu",R.id.beizhu);
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
            if(convertView == null){
                //获取外部的配置文件
                //LayoutInflater 布局填充器
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.image_text_two,null);
            }else{
                view = convertView;
            }
            ImageView photo = (ImageView) view.findViewById(R.id.photo);
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView price = (TextView) view.findViewById(R.id.price);
            RatingBar pingxin = (RatingBar) view.findViewById(R.id.ratingBar);
            TextView beizhu = (TextView) view.findViewById(R.id.beizhu);


            photo.setImageResource(Integer.parseInt(getList().get(position).get("photo").toString()));
            name.setText(getList().get(position).get("name").toString());
            price.setText(getList().get(position).get("price").toString());
            beizhu.setText(getList().get(position).get("beizhu").toString());
            pingxin.setRating(Float.parseFloat(getList().get(position).get("pingxin").toString()));

            return view;
        }
    }
}
