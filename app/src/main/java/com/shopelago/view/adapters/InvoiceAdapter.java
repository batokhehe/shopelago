package com.shopelago.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shopelago.R;
import com.shopelago.view.fragments.InvoiceFragment.OnListFragmentInteractionListener;
import com.shopelago.models.Product;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    private List<Product> mProduct;
    private OnListFragmentInteractionListener mListener;
    private Context mContext;

    public InvoiceAdapter(Context context, List<Product> items, OnListFragmentInteractionListener listener) {
        mContext = context;
        mProduct = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_invoice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mProduct.get(position);
        holder.tvProductName.setText(mProduct.get(position).getName());
        holder.tvProductPrice.setText(mProduct.get(position).getPrice());

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
        return mProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView tvProductName, tvProductPrice;
        public Product mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) view.findViewById(R.id.tvProductPrice);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvProductName.getText() + "'";
        }
    }
}
