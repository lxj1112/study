package com.hzyc.lxj.lxj05;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private Button add;
    private ImageView update;
    private EditText name,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString().trim();
                String phone1 = phone.getText().toString().trim();
                if(name1.equals("")){
                    Toast.makeText(Main2Activity.this,"姓名不能为空",Toast.LENGTH_LONG).show();
                }else if(phone1.equals("")){
                    Toast.makeText(Main2Activity.this,"手机不能为空",Toast.LENGTH_LONG).show();
                }else {
                    CreateDb db = new CreateDb(Main2Activity.this);
                    SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
                    sqLiteDatabase.execSQL("insert into pop (name,phone) values ('"+name1+"','"+phone1+"')");
                    Intent intent =new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
