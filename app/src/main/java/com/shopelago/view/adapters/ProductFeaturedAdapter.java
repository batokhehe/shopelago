package com.shopelago.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopelago.R;
import com.shopelago.models.ProductFeatured;

import java.util.ArrayList;
import java.util.List;

public class ProductFeaturedAdapter extends RecyclerView.Adapter<ProductFeaturedAdapter.ViewHolder> {

    private Context mContext;
    private List<ProductFeatured> mData = new ArrayList<>();
    private View view;
    private OnItemClickListener listener;

    public ProductFeaturedAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ProductFeaturedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_featured_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductFeaturedAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tvProductName.setText(mData.get(i).getName());
        viewHolder.tvProductPrice.setText(mData.get(i).getPrice());
        Glide.with(mContext)
                .load(mData.get(i).getImage())
                .override(60, 60) // resizing
                .centerCrop()
                .into(viewHolder.ivProductImage);
//                .placeholder(R.drawable.placeholder) // any placeholder to load at start
//                .error(R.drawable.imagenotfound)  // any image in case of error
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvProductName, tvProductPrice;
        public ImageView ivProductImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            ivProductImage = (ImageView) itemView.findViewById(R.id.ivProductImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(mData.get(position));
                    }
                }
            });
        }
    }

    public void setDatas(List<ProductFeatured> datas){
        this.mData = datas;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(ProductFeatured model);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
