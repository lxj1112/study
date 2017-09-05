package com.hzyc.lxj.lxj02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private EditText setValue;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setValue = (EditText) findViewById(R.id.setValue);
        back = (Button) findViewById(R.id.back);
        SharedPreferences sp = getSharedPreferences("data",0);
        String value = sp.getString("value","nothing");

        if(!"nothing".equals(value)){
            back.setText(value);
        }

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        int num = intent.getIntExtra("age",0);
        setValue.setText(name+"##"+num);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回跳  并且传值
                String backValue = setValue.getText().toString().trim();
                Intent intent1 = new Intent(Main3Activity.this,Main2Activity.class);
                intent1.putExtra("back",backValue);

                //回跳
                setResult(400,intent1);
                finish();//关闭
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //显示什么菜单文件呢？
        MenuInflater menuInflater = getMenuInflater();
        //填充菜单文件
        menuInflater.inflate(R.menu.menu_one,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.save :
                Toast.makeText(Main3Activity.this, "保存", Toast.LENGTH_SHORT).show();
            break;
            case  R.id.update :
                Toast.makeText(Main3Activity.this, "更新", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.delete :
                Toast.makeText(Main3Activity.this, "删除", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String value = back.getText().toString().trim();
        boolean bol = false;
        if(!value.equals("")){
            //保护的数据永久存储下来 使用数据存储方案
            //数据存储到手机的XML中
            SharedPreferences sp = getSharedPreferences("data",0);
            //创建到手机中
            //查看这个文件 虚拟机可以 真机不可以（没有root）
            //得到这个文件的编辑权限
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("value",value);
            bol = editor.commit();
            Toast.makeText(Main3Activity.this, "保护的状态="+bol, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Main3Activity.this, "保护的状态="+bol, Toast.LENGTH_SHORT).show();
        }
    }

}