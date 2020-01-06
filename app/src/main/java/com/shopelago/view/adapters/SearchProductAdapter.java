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

import com.bumptech.glide.Glide;
import com.shopelago.R;
import com.shopelago.view.fragments.SearchProductFragment.OnListFragmentInteractionListener;
import com.shopelago.models.Product;
import com.shopelago.models.Shop;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.ViewHolder> {

    private Context mContext;
    private List<Product> mProduct;
    private OnListFragmentInteractionListener mListener;
    private String searchText;

    public SearchProductAdapter(Context context, List<Product> productsList, OnListFragmentInteractionListener listener) {
        mContext = context;
        mProduct = productsList;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_search_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        String productName = mProduct.get(i).getName();
        SpannableStringBuilder sb = new SpannableStringBuilder(productName);
        if(searchText != null && searchText.length()>0){
            //color your text here
            int index = productName.toLowerCase().indexOf(searchText.toLowerCase());
            while(index > -1){
                sb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), index, index+searchText.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                index = productName.indexOf(searchText.toLowerCase(),index+1);
            }
            viewHolder.tvProductName.setText(sb);
        }else{
            viewHolder.tvProductName.setText(Html.fromHtml(productName));
        }
        //viewHolder.tvProductName.setText(mProduct.get(i).getName());
        viewHolder.tvProductPrice.setText(mProduct.get(i).getPrice());
        Glide.with(mContext)
                .load(mProduct.get(i).getImage())
                .override(120, 120) // resizing
                .centerCrop()
                .into(viewHolder.ivProductImage);

        Shop shop = mProduct.get(i).getShop();
        if(shop != null){
            viewHolder.tvShopName.setText(shop.getName());
            boolean isTrustedSeller = shop.isTrustedSeller();
            if(!isTrustedSeller){
                viewHolder.ivTrustedSeller.setVisibility(View.GONE);
            }
            viewHolder.tvShopLocation.setText(shop.getLocation());
        }

        double ratingPoint = mProduct.get(i).getRating();
        viewHolder.tvRatingPoint.setText(String.valueOf(ratingPoint));
        int rating = (int) ratingPoint;

        int imgRating = 0;
        switch (rating){
            case 1:
                imgRating = R.drawable.ic_rating_1;
                break;
            case 2:
                imgRating = R.drawable.ic_rating_2;
                break;
            case 3:
                imgRating = R.drawable.ic_rating_3;
                break;
            case 4:
                imgRating = R.drawable.ic_rating_4;
                break;
            case 5:
                imgRating = R.drawable.ic_rating_5;
                break;
        }
        Glide.with(mContext)
                .load(imgRating)
                .into(viewHolder.ivRating);

    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProductName, tvProductPrice, tvShopName, tvShopLocation, tvRatingPoint;
        public ImageView ivProductImage, ivTrustedSeller, ivRating;

        public ViewHolder(View view) {
            super(view);

            tvProductName = view.findViewById(R.id.tvProductName);
            tvProductPrice = view.findViewById(R.id.tvProductPrice);
            ivProductImage = view.findViewById(R.id.ivProductImage);
            tvShopName = view.findViewById(R.id.tvShopName);
            tvShopLocation = view.findViewById(R.id.tvShopLocation);
            tvRatingPoint = view.findViewById(R.id.tvRatingPoint);
            ivTrustedSeller = view.findViewById(R.id.ivTrustedSeller);
            ivRating = view.findViewById(R.id.ivRating);

        }
    }

    public void setFilter(List<Product> listProduct, String searchText) {
        mProduct = listProduct;
        this.searchText = searchText;
        notifyDataSetChanged();
    }
}
