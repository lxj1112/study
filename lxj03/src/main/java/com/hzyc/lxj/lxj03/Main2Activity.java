package com.hzyc.lxj.lxj03;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    private Button open,dialog,list,day,time;
    private String [] data = {"苹果","香蕉","芒果"};
    private DialogInterface.OnClickListener di = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case -1:
                    Toast.makeText(Main2Activity.this, "喜欢"+which, Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(Main2Activity.this, "不喜欢"+which, Toast.LENGTH_SHORT).show();
                    break;
                case -3:
                    Toast.makeText(Main2Activity.this, "一般"+which, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        open = (Button) findViewById(R.id.open);
        dialog = (Button) findViewById(R.id.dialog);
        list = (Button) findViewById(R.id.list);
        day = (Button) findViewById(R.id.day);
       time = (Button) findViewById(R.id.time);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出菜单比较简单
                PopupMenu popupMenu = new PopupMenu(Main2Activity.this,v);
                //菜单文件
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.menu_one,popupMenu.getMenu());
                //弹出的东西  show
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case  R.id.save :
                                Toast.makeText(Main2Activity.this, "保存", Toast.LENGTH_SHORT).show();
                                return true;
                            case  R.id.update :
                                Toast.makeText(Main2Activity.this, "更新", Toast.LENGTH_SHORT).show();
                                return true;
                            case  R.id.delete :
                                Toast.makeText(Main2Activity.this, "删除", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
            }
        });
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setTitle("问卷调查")
                        .setIcon(R.drawable.image1)
                        .setMessage("您喜欢长春吗?")
                        .setPositiveButton("喜欢", di)
                        .setNegativeButton("不喜欢", di)
                        .setNeutralButton("一般", di)
                        .show();
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setTitle("你喜欢什么水果")
                        .setIcon(R.drawable.image1)
                        .setMultiChoiceItems(data, new boolean[]{false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(Main2Activity.this, ""+which, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String sj = year + "-" +monthOfYear+1+ "-" + dayOfMonth;
                        Toast.makeText(Main2Activity.this, sj, Toast.LENGTH_SHORT).show();
                    }
                },year,month,day);

                dpd.show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog time = new TimePickerDialog(Main2Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    }
                },Calendar.HOUR_OF_DAY,Calendar.MINUTE,true);
                time.show();
            }
        });
    }
}
