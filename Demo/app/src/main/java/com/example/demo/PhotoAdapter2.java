package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoAdapter2 extends PagerAdapter {

    private Context  mContext2;
    private List<Photo2>  mListPhoto2;

    public PhotoAdapter2(Context mContext, List<Photo2> mListPhoto) {
        this.mContext2 = mContext;
        this.mListPhoto2 = mListPhoto;
    }


    @Override
    public Object instantiateItem(@NonNull  ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.photo2_item,container,false);

        ImageView imgPhoto2 =view.findViewById(R.id.img_photo2);
        Photo2 photo = mListPhoto2.get(position);
        if (photo != null){
//
//            Glide.with(mContext2).load(photo.getRescurceId2()).into(imgPhoto2);
            Glide.with(mContext2).load(photo.getRescurceId2()).into(imgPhoto2);
        }

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if(mListPhoto2 != null){
            return mListPhoto2.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view,  Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull  ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
