package com.example.yrj.gaoxiaobang1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Main2Activity extends AppCompatActivity {
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.content);
        textView.setMovementMethod(new ScrollingMovementMethod());
        Intent intent=getIntent();
        String data=intent.getStringExtra("url");
        Bmob.initialize(this,"aa2fc85163714c0297b551d9378ea242");
        BmobQuery<News>query=new BmobQuery<>();
        query.addWhereEqualTo("Url",data);
        query.findObjects(new FindListener<News>() {
            @Override
            public void done(List<News> list, BmobException e) {
                if (e==null){
                    for (News news:list){
                        String s=news.getContent();
                        textView.setText(s);
                    }
                }else{
                    Toast.makeText(Main2Activity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
