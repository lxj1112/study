package com.hzyc.lxj.lxj05;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    private TextView name,phone;
    private Button xiugai;
    private int id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        xiugai = (Button) findViewById(R.id.xiugai);
        Intent intent = getIntent();
        id1 = intent.getIntExtra("id",0);
        String name1 = intent.getStringExtra("name");
        String phone1 = intent.getStringExtra("phone");
        name.setText(name1);
        phone.setText(phone1);
        xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name11 = name.getText().toString().trim();
                String phone11 = phone.getText().toString().trim();
                if(name11.equals("")){
                    Toast.makeText(Main4Activity.this,"姓名不能为空",Toast.LENGTH_LONG).show();
                }else if(phone11.equals("")){
                    Toast.makeText(Main4Activity.this,"手机不能为空",Toast.LENGTH_LONG).show();
                }else {
                    CreateDb db = new CreateDb(Main4Activity.this);
                    SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
                    sqLiteDatabase.execSQL("update pop set name = '"+name11+"' ,phone = '"+phone11+"' where id = '"+id1+"'");
                    Intent intent =new Intent(Main4Activity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(Main4Activity.this,"修改成功",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
