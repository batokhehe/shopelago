package com.shopelago.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopelago.R;
import com.shopelago.models.Partner;

import java.util.List;

public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.ViewHolder> {

    private Context mContext;
    private List<Partner> mPartner;

    public PartnerAdapter(Context context, List<Partner> Partners) {
        mContext = context;
        mPartner = Partners;
    }

    @NonNull
    @Override
    public PartnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_exclusive_partner, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartnerAdapter.ViewHolder viewHolder, int i) {
        Glide.with(mContext)
                .load(mPartner.get(i).getImage())
                .fitCenter()
                .into(viewHolder.ivPartnerImage);
//                .placeholder(R.drawable.placeholder) // any placeholder to load at start
//                .error(R.drawable.imagenotfound)  // any image in case of error
    }

    @Override
    public int getItemCount() {
        return mPartner.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivPartnerImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPartnerImage = (ImageView) itemView.findViewById(R.id.ivPartnerImage);
        }
    }
}
