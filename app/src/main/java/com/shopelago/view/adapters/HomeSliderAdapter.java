package com.shopelago.view.adapters;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class HomeSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide("http://bhovdair.com/projects/shopelago/Banner1.jpg");
                break;
            case 1:
                viewHolder.bindImageSlide("http://bhovdair.com/projects/shopelago/Banner2.jpg");
                break;
            case 2:
                viewHolder.bindImageSlide("http://bhovdair.com/projects/shopelago/Banner3.jpg");
                break;
        }
    }
}