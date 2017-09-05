package com.hzyc.lxj.lxj02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    private ListView listView;
    private TextView shouye;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.listView);
        shouye = (TextView) findViewById(R.id.t8);
        shouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent); //开始跳转
            }
        });
        listView.setAdapter(new MyAdapter());
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("name","小刘");
                intent.putExtra("age",18);
                startActivityForResult(intent,200);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 200 && resultCode == 400){
            String backValue = data.getStringExtra("back");
            Toast.makeText(Main2Activity.this, backValue, Toast.LENGTH_SHORT).show();
        }
    }
    public List<Map<String,Object>> getList(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 1;i<=16; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("photo",R.drawable.image1);
            map.put("name","数据"+i);
            map.put("price","价格="+i);
            map.put("pingxin",i);
            map.put("beizhu","小米手机");
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
                view = LayoutInflater.from(Main2Activity.this).inflate(R.layout.image_text_two,null);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //填充菜单文件
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuone,menu);
    }

    //2选择

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.gouwu :
                Toast.makeText(Main2Activity.this, "购物", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.meishi :
                Toast.makeText(Main2Activity.this, "美食", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.yule :
                Toast.makeText(Main2Activity.this, "娱乐", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.life :
                Toast.makeText(Main2Activity.this, "生活", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.shuma :
                Toast.makeText(Main2Activity.this, "数码", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.huzhuang :
                Toast.makeText(Main2Activity.this, "服装", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.xiebao :
                Toast.makeText(Main2Activity.this, "鞋包", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.all :
                Toast.makeText(Main2Activity.this, "全部", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }
}