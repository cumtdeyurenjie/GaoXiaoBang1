package com.example.yrj.gaoxiaobang1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
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
    ConvenientBanner banner;
    private String[] images = {"http://bmob-cdn-17361.b0.upaiyun.com/2018/03/16/64e0b7cd408f46288065118e80535f17.png",
            "http://bmob-cdn-17361.b0.upaiyun.com/2018/03/16/2e9cc9da407625228000aaf72e4acb6a.png",
            "http://bmob-cdn-17361.b0.upaiyun.com/2018/03/16/a4bcd8ba40f6220a80d86a55957850d7.png",
    };
    List<String>netWorkImage=new ArrayList<>();
    List<News> aNews;
    RecyclerView recycler_news;
    private LinearLayoutManager linearLayoutManager;
    private Adapter1 adapter;
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
        initBanner();
    }
    private void initBanner() {
        netWorkImage= Arrays.asList(images);
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public NetWorkHolderView createHolder() {
                return new NetWorkHolderView();
            }
        },netWorkImage)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_LEFT)
                .setPageIndicator(new int[]{R.drawable.indicator_gray,R.drawable.indicator_red})
                .startTurning(2000)
                .setScrollDuration(1500);
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),Main2Activity.class);
                String Url=images[position];
                intent.putExtra("url",Url);
                startActivity(intent);
            }
        });
    }

    private void initNews() {
        aNews =new ArrayList<News>();
        BmobQuery<News> query=new BmobQuery<News>();
        query.addWhereEqualTo("biaoshi","pengcheng");
        query.findObjects(new FindListener<News>() {
            @Override
            public void done(List<News> list, BmobException e) {
                if (e==null){
                    aNews =list;
                    adapter =new Adapter1(aNews);
                    recycler_news.setAdapter(adapter);
                    adapter.setmHeaderView(banner);
                }else{
                    Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void initView() {
        View header=LayoutInflater.from(getActivity()).inflate(R.layout.rv_header_banner,null);
        banner=header.findViewById(R.id.banner);
        banner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,500));
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

    private class NetWorkHolderView implements Holder<String>{
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            View view=LayoutInflater.from(context).inflate(R.layout.rv_header_img,null);
            imageView=view.findViewById(R.id.iv_head);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Log.d("imageUrl","UPdataUI:"+data);
            Glide.with(context).load(data).placeholder(R.mipmap.ic_launcher_round).into(imageView);
        }
    }

    /**  private void initLists() {
        list_path.add(R.drawable.jia);
        list_path.add(R.drawable.lao);
        list_path.add(R.drawable.ban);
    }*/
}
