package com.example.yrj.gaoxiaobang1;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
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
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };
    List<String>netWorkImage=new ArrayList<>();
    List<News> aNews;
    RecyclerView recycler_news;
    private LinearLayoutManager linearLayoutManager;
    private Adapter1 adapter;
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
                .setScrollDuration(1500);
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
        banner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400));
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
