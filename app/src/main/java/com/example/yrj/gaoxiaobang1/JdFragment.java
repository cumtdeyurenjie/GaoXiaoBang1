package com.example.yrj.gaoxiaobang1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by YRJ on 2018/3/7.
 */

public class JdFragment extends Fragment {
    List<News> aNews;
    RecyclerView recycler_news;
    private LinearLayoutManager linearLayoutManager;
    private Adapter2 adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getView()==null){
            View view =inflater.inflate(R.layout.huiwang,container,false);
            return view;
        }else {
            return getView();
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bmob.initialize(getActivity(),"aa2fc85163714c0297b551d9378ea242");
        initView();
        initNews();
    }

    private void initNews() {
        aNews =new ArrayList<News>();
        BmobQuery<News> query=new BmobQuery<News>();
        query.addWhereEqualTo("biaoshi","huiwang");
        query.findObjects(new FindListener<News>() {
            @Override
            public void done(List<News> list, BmobException e) {
                if (e==null){
                    aNews =list;
                    adapter =new Adapter2(aNews);
                    recycler_news.setAdapter(adapter);
                }else{
                    Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        recycler_news=getView().findViewById(R.id.recycler_view3);
        linearLayoutManager=new LinearLayoutManager(getContext());
        recycler_news.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recycler_news.getContext(),linearLayoutManager.getOrientation());
        recycler_news.addItemDecoration(dividerItemDecoration);
    }
}
