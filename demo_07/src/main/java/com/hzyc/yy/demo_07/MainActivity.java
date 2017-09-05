package com.hzyc.yy.demo_07;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button save, read;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = (Button) findViewById(R.id.save);
        read = (Button) findViewById(R.id.read);


        //xml 文件存储

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = "张三";
                String psd = "123456";
                SharedPreferences sharedPreferences = getSharedPreferences("data", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", user);
                editor.putString("psd", psd);
                boolean bol = editor.commit();

                //editor.remove()
                //editor.putStringSet()

                Toast.makeText(MainActivity.this, "保存状态="+bol, Toast.LENGTH_SHORT).show();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("data", 0);
                String name = sharedPreferences.getString("name","nothing1");
                String psd = sharedPreferences.getString("psd","nothing2");

                Toast.makeText(MainActivity.this,name+"@@"+psd, Toast.LENGTH_SHORT).show();
            }
        });
        //IO 存储 内部 外部
        //数据库存储 sqlite
        //nerwork 网络
    }
}
