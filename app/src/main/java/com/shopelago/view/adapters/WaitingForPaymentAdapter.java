package com.shopelago.view.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shopelago.R;
import com.shopelago.view.fragments.InvoiceFragment;
import com.shopelago.view.fragments.PaymentGuideFragment;
import com.shopelago.view.fragments.WaitingPaymentFragment.OnListFragmentInteractionListener;
import com.shopelago.models.WaitingForPayment;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class WaitingForPaymentAdapter extends RecyclerView.Adapter<WaitingForPaymentAdapter.ViewHolder> {

    private List<WaitingForPayment> mWaitingForPayment;
    private OnListFragmentInteractionListener mListener;
    private Context mContext;
    private Fragment mFragment;

    public WaitingForPaymentAdapter(Context context, List<WaitingForPayment> items, OnListFragmentInteractionListener listener, Fragment fragment) {
        mContext = context;
        mWaitingForPayment = items;
        mListener = listener;
        mFragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_waiting_for_payment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mWaitingForPayment.get(position);
        holder.tvDate.setText(mWaitingForPayment.get(position).getDate());
        holder.tvTotalPayment.setText(mWaitingForPayment.get(position).getTotalPayment());
        holder.tvPaymentMethod.setText(mWaitingForPayment.get(position).getPaymentMethod());
        holder.tvPaymentAccount.setText(mWaitingForPayment.get(position).getPaymentAccount());

        holder.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCancelModal();
            }
        });

        holder.btnPaymentGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPaymentGuide();
            }
        });

        holder.btnInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoInvoice();
            }
        });

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
        return mWaitingForPayment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView tvDate, tvTotalPayment, tvPaymentMethod, tvPaymentAccount, tvCancel;
        public Button btnPaymentGuide, btnInvoice;
        public WaitingForPayment mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvTotalPayment = (TextView) view.findViewById(R.id.tvTotalPayment);
            tvPaymentMethod = (TextView) view.findViewById(R.id.tvPaymentMethod);
            tvPaymentAccount = (TextView) view.findViewById(R.id.tvPaymentAccount);
            tvCancel = (TextView) view.findViewById(R.id.tvCancel);

            btnPaymentGuide = (Button) view.findViewById(R.id.btnPaymentGuide);
            btnInvoice = (Button) view.findViewById(R.id.btnInvoice);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvDate.getText() + "'";
        }
    }

    private void showCancelModal(){
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.modal_cancel_transaction);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void gotoPaymentGuide(){
        PaymentGuideFragment fragment = new PaymentGuideFragment();
        mFragment.getFragmentManager().beginTransaction().
                replace(R.id.content, fragment).
                addToBackStack(null).
                commit();
    }

    private void gotoInvoice(){
        InvoiceFragment fragment = new InvoiceFragment();
        mFragment.getFragmentManager().beginTransaction().
                replace(R.id.content, fragment).
                addToBackStack(null).
                commit();
    }
}
