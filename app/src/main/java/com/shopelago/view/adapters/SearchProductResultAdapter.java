package com.shopelago.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shopelago.R;
import com.shopelago.view.fragments.SearchFragment.OnListFragmentInteractionListener;
import com.shopelago.models.Product;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SearchProductResultAdapter extends RecyclerView.Adapter<SearchProductResultAdapter.ViewHolder> {

    private Context mContext;
    private List<Product> mProduct;
    List<Product> mProductModel;
    private OnListFragmentInteractionListener mListener;
    String searchText;

    public SearchProductResultAdapter(Context context, List<Product> listProduct, OnListFragmentInteractionListener listener) {
        mContext = context;
        mProduct = listProduct;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_search_product_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        String name = mProduct.get(i).getName();
        SpannableStringBuilder sb = new SpannableStringBuilder(name);
        if(searchText != null && searchText.length()>0){
            //color your text here
            int index = name.toLowerCase().indexOf(searchText.toLowerCase());
            while(index > -1){
                sb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), index, index+searchText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                index = name.indexOf(searchText.toLowerCase(),index+1);
            }
            viewHolder.tvName.setText(sb);
        }else{
            viewHolder.tvName.setText(Html.fromHtml(name));
        }
//        int index = name.toLowerCase().indexOf(searchText.toLowerCase());
//        SpannableStringBuilder sb = new SpannableStringBuilder(name);
//        while(index > -1){
//            sb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), index, index+searchText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            index = name.indexOf(searchText.toLowerCase(),index+1);
//        }
//        viewHolder.tvName.setText(sb);
//        if(searchText != null && searchText.length()>0){
//            //color your text here
//            int index = name.toLowerCase().indexOf(searchText.toLowerCase());
//            while(index > -1){
//                ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLACK); //specify color here
//                sb.setSpan(fcs, index, index+searchText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//                index = name.indexOf(searchText.toLowerCase(),index+1);
//            }
//            viewHolder.tvName.setText(sb);
//        }else{
//            viewHolder.tvName.setText(Html.fromHtml(name));
//        }
    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ViewHolder(View view) {
            super(view);

            tvName = view.findViewById(R.id.tvName);

        }
    }

    public void setFilter(List<Product> listProduct, String searchText) {
        mProduct = listProduct;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

}
