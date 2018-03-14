package com.example.yrj.gaoxiaobang1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobQuery;

public class Main2Activity extends AppCompatActivity {
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.content);
        textView.setMovementMethod(new ScrollingMovementMethod());
        Intent intent=getIntent();
        String data=intent.getStringExtra("name");
        textView.setText(data);

    }
}
