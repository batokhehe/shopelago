package com.shopelago.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shopelago.R;
import com.shopelago.view.fragments.MessageSentFragment.OnListFragmentInteractionListener;
import com.shopelago.models.Message;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MessageSentAdapter extends RecyclerView.Adapter<MessageSentAdapter.ViewHolder> {

    private Context mContext;
    private List<Message> mMessage;
    private OnListFragmentInteractionListener mListener;

    public MessageSentAdapter(Context context, List<Message> listMessage, OnListFragmentInteractionListener listener) {
        mContext = context;
        mMessage = listMessage;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.tvName.setText(mMessage.get(i).getName());
        viewHolder.tvDate.setText(mMessage.get(i).getDate());
        viewHolder.tvMessage.setText(mMessage.get(i).getMessage());
//        Glide.with(mContext)
//                .load(mMessage.get(i).getImage())
//                .override(120, 120) // resizing
//                .centerCrop()
//                .into(viewHolder.ivProfilePicture);
                Glide.with(mContext)
                .load(mMessage.get(i).getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.ivProfilePicture);
    }

    @Override
    public int getItemCount() {
        return mMessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvDate, tvMessage;
        public ImageView ivProfilePicture;

        public ViewHolder(View view) {
            super(view);

            tvName = view.findViewById(R.id.tvName);
            tvDate = view.findViewById(R.id.tvDate);
            tvMessage = view.findViewById(R.id.tvMessage);
            ivProfilePicture = view.findViewById(R.id.ivProfilePicture);

        }
    }

}
