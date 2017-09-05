package com.hzyc.lxj.lxj05;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private TextView name11,phone11;
    private ImageView dadianhua;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        name11 = (TextView) findViewById(R.id.name11);
        phone11 = (TextView) findViewById(R.id.phone11);
        dadianhua = (ImageView) findViewById(R.id.dadianhua);
        dadianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phone11.getText().toString();
                Intent intent2 = new Intent();
                //设置拨打电话的动作
                intent2.setAction(Intent.ACTION_CALL);
                 //设置拨打电话的号码
                 intent2.setData(Uri.parse("tel:" + phone));
                //开启打电话的意图
                 startActivity(intent2);
            }
        });
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        name11.setText(name);
        phone11.setText(phone);
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
            case  R.id.update :
                Intent intent = new Intent(Main3Activity.this,Main4Activity.class);
                String name = name11.getText().toString();
                String phone = phone11.getText().toString();
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                startActivity(intent);
                break;
            case  R.id.delete :
                CreateDb db = new CreateDb(Main3Activity.this);
                SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
                sqLiteDatabase.execSQL("delete from pop where id = '"+id+"'");
                Intent intent1 =new Intent(Main3Activity.this,MainActivity.class);
                startActivity(intent1);
                Toast.makeText(Main3Activity.this,"删除成功",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
