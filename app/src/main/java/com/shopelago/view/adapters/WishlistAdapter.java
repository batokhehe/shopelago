package com.shopelago.view.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopelago.R;
import com.shopelago.view.fragments.WishlistFragment.OnListFragmentInteractionListener;
import com.shopelago.models.Product;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private Context mContext;
    private List<Product> mProduct;
    private OnListFragmentInteractionListener mListener;

    public WishlistAdapter(Context context, List<Product> productsList, OnListFragmentInteractionListener listener) {
        mContext = context;
        mProduct = productsList;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_wishlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.tvProductName.setText(mProduct.get(i).getName());
        viewHolder.tvProductPrice.setText(mProduct.get(i).getPrice());
        Glide.with(mContext)
                .load(mProduct.get(i).getImage())
                .override(120, 120) // resizing
                .centerCrop()
                .into(viewHolder.ivProductImage);

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteModal(mProduct.get(i).getName());
            }
        });

        viewHolder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAtcModal();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProductName, tvProductPrice;
        public ImageView ivProductImage;
        public Button btnBuy, btnDelete;

        public ViewHolder(View view) {
            super(view);

            tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) view.findViewById(R.id.tvProductPrice);
            ivProductImage = (ImageView) view.findViewById(R.id.ivProductImage);

            btnDelete = (Button) view.findViewById(R.id.btnDelete);
            btnBuy = (Button) view.findViewById(R.id.btnBuy);
        }
    }

    private void showDeleteModal(String name){
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.modal_delete_wishlist);
        TextView tvProductName = dialog.findViewById(R.id.tvProductName);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        tvProductName.setText(name);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showAtcModal(){
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.modal_atc_success);
        dialog.show();

        // Hide after some seconds
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        };

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);
    }
}
