package com.example.finalproject.Adapter;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide("https://i.ibb.co/mhb2wJH/wp4385848-student-education-wallpapers.jpg");
                break;
            case 1:
                viewHolder.bindImageSlide("https://i.ibb.co/6HH68WF/wp4385845-student-education-wallpapers.jpg");
                break;
            case 2:
                viewHolder.bindImageSlide("https://i.ibb.co/jMnZCSh/wp4385873-student-education-wallpapers.jpg");
                break;
        }
    }
}
