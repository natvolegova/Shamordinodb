package com.example.shamordinodb.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.shamordinodb.R;

import java.util.ArrayList;

public class ImageSliderAdapter extends PagerAdapter {
    Context mContext;
    String[] sliderImages;

    public ImageSliderAdapter(Context context, String[] sliderImages) {
        this.mContext = context;
        this.sliderImages = sliderImages;
    }

    @Override
    public int getCount() {
        return sliderImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ImageView) object);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Resources res = mContext.getResources();
        View view = LayoutInflater.from(mContext).inflate(R.layout.pager_item, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        int resID = res.getIdentifier(sliderImages[position], "raw", mContext.getPackageName());

        imageView.setImageResource(resID);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
