package com.shopelago.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopelago.R;
import com.shopelago.view.fragments.OrderDetailFragment.OnListFragmentInteractionListener;
import com.shopelago.models.OrderDetail;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private List<OrderDetail> mOrderDetail;
    private OnListFragmentInteractionListener mListener;
    private Context mContext;

    public OrderDetailAdapter(Context context, List<OrderDetail> items, OnListFragmentInteractionListener listener) {
        mContext = context;
        mOrderDetail = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_order_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mOrderDetail.get(position);
        holder.tvProductName.setText(mOrderDetail.get(position).getName());
        holder.tvProductDesc.setText(mOrderDetail.get(position).getDesc());
        holder.tvProductPrice.setText(mOrderDetail.get(position).getPrice());
        Glide.with(mContext)
                .load(mOrderDetail.get(position).getImage())
                .override(100, 100) // resizing
                .centerCrop()
                .into(holder.ivProductImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrderDetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView tvProductName, tvProductDesc, tvProductPrice;
        public ImageView ivProductImage;
        public OrderDetail mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            tvProductDesc = (TextView) view.findViewById(R.id.tvProductDesc);
            tvProductPrice = (TextView) view.findViewById(R.id.tvProductPrice);
            ivProductImage = (ImageView) view.findViewById(R.id.ivProductImage);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvProductName.getText() + "'";
        }
    }
}
