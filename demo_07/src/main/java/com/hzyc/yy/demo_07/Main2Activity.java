package com.hzyc.yy.demo_07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main2Activity extends AppCompatActivity {


    private Button save, read;
    private EditText name, psd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        save = (Button) findViewById(R.id.save);
        read = (Button) findViewById(R.id.read);
        name = (EditText) findViewById(R.id.name);
        psd = (EditText) findViewById(R.id.psd);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IO
                //存储文件 输出流（文件不存在可以创建）
                //data文件夹中创建
                String name1 = name.getText().toString().trim();
                String psd1 = psd.getText().toString().trim();
                FileOutputStream outputStream = null;
                boolean bol = false;
                try {
                    String value = name1 + "@@" + psd1;
                    outputStream = openFileOutput("data.txt", 0);
                    outputStream.write(value.getBytes());
                    bol = true;
                    Toast.makeText(Main2Activity.this, "@@@@@@" + bol, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileInputStream input = null;
                try {
                    input = openFileInput("data.txt");
                    byte [] data = new byte[1024];

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    int len = 0;
                    while((len = input.read(data)) != -1){
                            out.write(data,0,len);
                    }
                    String value = out.toString();



                    String [] str = value.split("@@");
                    Toast.makeText(Main2Activity.this, str[0], Toast.LENGTH_SHORT).show();
                    Toast.makeText(Main2Activity.this, str[1], Toast.LENGTH_SHORT).show();
                    input.close();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
