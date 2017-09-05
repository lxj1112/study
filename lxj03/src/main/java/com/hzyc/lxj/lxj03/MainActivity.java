package com.hzyc.lxj.lxj03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView imgPic;
    private Button btnAlpha, btnScale, btnTranslate, btnRotate;
    private Animation myAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiView();

        initData();
    }
    private void intiView() {
        imgPic = (ImageView) findViewById(R.id.imageView);
        btnAlpha = (Button) findViewById(R.id.button);
        btnScale = (Button) findViewById(R.id.button2);
        btnTranslate = (Button) findViewById(R.id.button3);
        btnRotate = (Button) findViewById(R.id.button4);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        btnAlpha.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnTranslate.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                /**
                 * 使用XML中的动画效果 第一个参数Context为程序的上下文 第二个参数id为动画XML文件的引用
                 */
                myAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                imgPic.startAnimation(myAnimation);
                break;

            case R.id.button2:
                myAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
                imgPic.startAnimation(myAnimation);
                break;

            case R.id.button3:
                myAnimation = AnimationUtils.loadAnimation(this,
                        R.anim.translate);
                imgPic.startAnimation(myAnimation);
                break;

            case R.id.button4:
                myAnimation = AnimationUtils
                        .loadAnimation(this, R.anim.rotate);
                imgPic.startAnimation(myAnimation);
                break;

        }
    }
}
