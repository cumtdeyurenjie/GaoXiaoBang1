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

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
/**
 * Created by YRJ on 2018/3/7.
 */
//添加Recyclerview的点击事件
    //填充数据
public class PcFragment extends Fragment {
    List<News> aNews;
    RecyclerView recycler_news;
    private LinearLayoutManager linearLayoutManager;
    private Adapter2 adapter;
    //ArrayList<Integer>list_path=new ArrayList<>();//存放轮播图的图片路径
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getView()==null){
           View view =inflater.inflate(R.layout.pengcheng,container,false);
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
        query.addWhereEqualTo("biaoshi","pengcheng");
        query.findObjects(new FindListener<News>() {
            @Override
            public void done(List<News> list, BmobException e) {
                if (e==null){
                    Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
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
        recycler_news=getView().findViewById(R.id.recycler_view2);
       /** banner.setImageLoader(new GlideImageLoader());
        initLists();
        banner.setImages(list_path);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

                  Toast.makeText(getActivity(), "aaaa", Toast.LENGTH_SHORT).show();

            }
        });
        banner.start();*/
        linearLayoutManager=new LinearLayoutManager(getContext());
        recycler_news.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recycler_news.getContext(),linearLayoutManager.getOrientation());
        recycler_news.addItemDecoration(dividerItemDecoration);
    }

  /**  private void initLists() {
        list_path.add(R.drawable.jia);
        list_path.add(R.drawable.lao);
        list_path.add(R.drawable.ban);
    }*/
}
