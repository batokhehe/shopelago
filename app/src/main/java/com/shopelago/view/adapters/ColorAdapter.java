package com.shopelago.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopelago.R;
import com.shopelago.models.Color;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {

    private Context mContext;
    private List<Color> mColor;

    public ColorAdapter(Context context, List<Color> Colors) {
        mContext = context;
        mColor = Colors;
    }

    @NonNull
    @Override
    public ColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_color, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvColorName.setText(mColor.get(i).getName());
        viewHolder.ivColorImage.setColorFilter(android.graphics.Color.parseColor(mColor.get(i).getImage()));
    }

    @Override
    public int getItemCount() {
        return mColor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvColorName;
        public ImageView ivColorImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvColorName = (TextView) itemView.findViewById(R.id.tvColorName);
            ivColorImage = (ImageView) itemView.findViewById(R.id.ivColorImage);
        }
    }
}
