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
import com.shopelago.view.fragments.PurchaseListFragment.OnListFragmentInteractionListener;
import com.shopelago.models.PurchaseList;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PurchaseListAdapter extends RecyclerView.Adapter<PurchaseListAdapter.ViewHolder> {

    private List<PurchaseList> mPurchaseList;
//    private OnListFragmentInteractionListener mListener;
    private Context mContext;
    private OnItemClickListener listener;

    public PurchaseListAdapter(Context context, List<PurchaseList> items) {
        mContext = context;
        mPurchaseList = items;
//        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_purchase_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mPurchaseList.get(position);
        String status = mPurchaseList.get(position).getStatus();
        int color = 0;
        if(status.equals("2")){
            status = String.valueOf(mContext.getResources().getString(R.string.done));
            color = mContext.getResources().getColor(R.color.light_green);
        } else if(status.equals("1")){
            status = String.valueOf(mContext.getResources().getString(R.string.send));
            color = mContext.getResources().getColor(R.color.orange);
        } else if(status.equals("3")){
            status = String.valueOf(mContext.getResources().getString(R.string.order_canceled));
            color = mContext.getResources().getColor(R.color.red);
        }
        holder.tvStatus.setText(status);
        holder.tvStatus.setBackgroundColor(color);
        holder.tvDate.setText(mPurchaseList.get(position).getDate());
        holder.tvInvNumber.setText(mPurchaseList.get(position).getInvNumber());

        int Count = Integer.parseInt(mPurchaseList.get(position).getCount());
        if(Count > 1){
            holder.tvCount.setText(String.format("+%s Produk Lainnya", String.valueOf(Count - 1)));
        } else {
            holder.tvCount.setVisibility(View.GONE);
        }
        holder.tvProductName.setText(mPurchaseList.get(position).getName());
        holder.tvProductPrice.setText(mPurchaseList.get(position).getPrice());
        Glide.with(mContext)
                .load(mPurchaseList.get(position).getImage())
                .override(100, 100) // resizing
                .centerCrop()
                .into(holder.ivProductImage);

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mPurchaseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView tvStatus, tvInvNumber, tvDate, tvProductName, tvCount, tvProductPrice;
        public ImageView ivProductImage;
        public PurchaseList mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvStatus = (TextView) mView.findViewById(R.id.tvStatus);
            tvInvNumber = (TextView) mView.findViewById(R.id.tvInvNumber);
            tvDate = (TextView) mView.findViewById(R.id.tvDate);
            tvCount = (TextView) mView.findViewById(R.id.tvCount);
            tvProductName = (TextView) mView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) mView.findViewById(R.id.tvProductPrice);
            ivProductImage = (ImageView) mView.findViewById(R.id.ivProductImage);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(mPurchaseList.get(position));
                    }
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvProductName.getText() + "'";
        }
    }

    public interface OnItemClickListener{
        void onItemClick(PurchaseList purchaseList);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
