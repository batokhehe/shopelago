package com.shopelago.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopelago.R;
import com.shopelago.view.fragments.SearchFragment.OnListFragmentInteractionListener;
import com.shopelago.models.Shop;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SearchShopResultAdapter extends RecyclerView.Adapter<SearchShopResultAdapter.ViewHolder> {

    private Context mContext;
    private List<Shop> mShop;
    private OnListFragmentInteractionListener mListener;
    String searchText;

    public SearchShopResultAdapter(Context context, List<Shop> listShop, OnListFragmentInteractionListener listener) {
        mContext = context;
        mShop = listShop;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_search_shop_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        String shopName = mShop.get(i).getName();
        viewHolder.tvName.setText(shopName);
        boolean isTrustedSeller = mShop.get(i).isTrustedSeller();
        if(!isTrustedSeller){
            viewHolder.ivTrustedSeller.setVisibility(View.GONE);
        }

        SpannableStringBuilder sb = new SpannableStringBuilder(shopName);
        if(searchText != null && searchText.length()>0){
            //color your text here
            int index = shopName.toLowerCase().indexOf(searchText.toLowerCase());
            while(index > -1){
                sb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), index, index+searchText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                index = shopName.indexOf(searchText.toLowerCase(),index+1);
            }
            viewHolder.tvName.setText(sb);
        }else{
            viewHolder.tvName.setText(Html.fromHtml(shopName));
        }
//        if(searchText != null && searchText.length()>0){
//            //color your text here
//            int index = shopName.toLowerCase().indexOf(searchText.toLowerCase());
//            while(index > -1){
//                ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(158, 158, 158)); //specify color here
//                sb.setSpan(fcs, index, index+searchText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//                index = shopName.indexOf(searchText.toLowerCase(),index+1);
//            }
//            viewHolder.tvName.setText(sb);
//        }else{
//            viewHolder.tvName.setText(Html.fromHtml(shopName));
//        }
    }

    @Override
    public int getItemCount() {
        return mShop.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivTrustedSeller;

        public ViewHolder(View view) {
            super(view);

            tvName = view.findViewById(R.id.tvName);
            ivTrustedSeller = view.findViewById(R.id.ivTrustedSeller);

        }
    }

    public void setFilter(List<Shop> listShop, String searchText) {
        mShop = listShop;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

}
