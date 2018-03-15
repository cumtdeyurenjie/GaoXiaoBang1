package com.example.yrj.gaoxiaobang1;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by YRJ on 2018/3/7.
 */

public class News {
    private String name;
    private String biaoshi;
    private String data;
    private String content;
    private String Url;
    public News(String name, String data,String content,String biaoshi){
        this.biaoshi=biaoshi;
        this.data=data;
        this.content=content;
        this.name=name;

    }
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBiaoshi() {
        return biaoshi;
    }

    public void setBiaoshi(String biaoshi) {
        this.biaoshi = biaoshi;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        this.Url= url;
    }
}
