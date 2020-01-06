package com.shopelago.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.view.adapters.SearchProductResultAdapter;
import com.shopelago.view.adapters.SearchShopResultAdapter;
import com.shopelago.models.Product;
import com.shopelago.models.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SearchFragment extends Fragment {

    // TODO: Customize parameter argument names
    // TODO: Customize parameters
    private MainActivity mainActivity;
    private View rootView;
    private Activity activity;
    private Context context;
    private Toolbar toolbar, toolbarHome;
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<Product> listProduct;
    private List<Shop> listShop;
    private RecyclerView rvProductResult, rvShopResult;
    private TextView tvLoadMoreProduct, tvLoadMoreShop;
    private EditText etSearch;
    private SearchProductResultAdapter productAdapter;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private List<Product> filteredListProduct;
    private List<Shop> filteredListShop;
    private View llProductResult, llShopResult, tvEmptyProductResult, tvEmptyShopResult;
    private SearchShopResultAdapter shopAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SearchFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        context = getContext();
        mainActivity = (MainActivity) getActivity();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search_all, container, false);
        initProductResult();
        initShopResult();
        LinearLayout layoutSearch = rootView.findViewById(R.id.layoutSearchAll);
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        etSearch = toolbarHome.findViewById(R.id.etSearch);

        llProductResult = rootView.findViewById(R.id.llProductResult);
        tvEmptyProductResult = rootView.findViewById(R.id.tvEmptyProductResult);
        llShopResult = rootView.findViewById(R.id.llShopResult);
        tvEmptyShopResult = rootView.findViewById(R.id.tvEmptyShopResult);

        String q = etSearch.getText().toString();
        filter(q);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Search", "afterTextChanged: " + s.toString());
                String search = s.toString();
                filter(search);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        layoutSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(context);
                return false;
            }
        });

        tvLoadMoreProduct = rootView.findViewById(R.id.tvLoadMoreProduct);
        tvLoadMoreShop = rootView.findViewById(R.id.tvLoadMoreShop);

        tvLoadMoreProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });

        tvLoadMoreShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        final EditText etSearch = mainActivity.findViewById(R.id.etSearch);
//        etSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(etSearch.length() > 0){
//                    Toast.makeText(context, etSearch.getText(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        return rootView;
    }

    private void filter(String search){
        filteredListProduct = new ArrayList<>();
        for (Product p : listProduct){
            if(p.getName().toLowerCase().contains(search.toLowerCase())){
                filteredListProduct.add(p);
            }
        }
        productAdapter.setFilter(filteredListProduct, search);
        toogleEmptyProduct(filteredListProduct);

        filteredListShop = new ArrayList<>();
        for (Shop s : listShop){
            if(s.getName().toLowerCase().contains(search.toLowerCase())){
                filteredListShop.add(s);
            }
        }
        shopAdapter.setFilter(filteredListShop, search);
        toogleEmptyShop(filteredListShop);
    }

    private void toogleEmptyProduct(List<Product> filteredList) {
        if(filteredList.size() < 1){
            llProductResult.setVisibility(View.GONE);
            tvEmptyProductResult.setVisibility(View.VISIBLE);
        } else {
            llProductResult.setVisibility(View.VISIBLE);
            tvEmptyProductResult.setVisibility(View.GONE);
        }
    }

    private void toogleEmptyShop(List<Shop> filteredList) {
        if(filteredList.size() < 1){
            llShopResult.setVisibility(View.GONE);
            tvEmptyShopResult.setVisibility(View.VISIBLE);
        } else {
            llShopResult.setVisibility(View.VISIBLE);
            tvEmptyShopResult.setVisibility(View.GONE);
        }
    }
    
    public static void hideKeyboard( Context context ) {

        try {
            InputMethodManager inputManager = ( InputMethodManager ) context.getSystemService( Context.INPUT_METHOD_SERVICE );

            View view = ( ( Activity ) context ).getCurrentFocus();
            if ( view != null ) {
                inputManager.hideSoftInputFromWindow( view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private void initProductResult() {
        listProduct = new ArrayList<>();
        listProduct.add(new Product("Samsung S8 - AUTHENTIC BNIB!", "", ""));
        listProduct.add(new Product("Samsung J2 Prime with Bonus Antigores Front absolute black", "", ""));
        listProduct.add(new Product("Samsung Galaxy S10+ Brand New In Box (BNIB)", "", ""));
        listProduct.add(new Product("Samsung S3 Mini - Bekas 4 bulan pakai", "", ""));
        listProduct.add(new Product("Xiaomi", "", ""));
        rvProductResult = rootView.findViewById(R.id.rvProductResult);
        rvProductResult.setNestedScrollingEnabled(false);
        productAdapter = new SearchProductResultAdapter(getContext(),listProduct, mListener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        rvProductResult.setLayoutManager(layoutManager);
        rvProductResult.setAdapter(productAdapter);
    }

    private void initShopResult() {
        listShop = new ArrayList<>();
        listShop.add(new Shop("Samsung Official Store", true));
        listShop.add(new Shop("Samsung Electronic", true));
        listShop.add(new Shop("Samsung Distributor", true));
        listShop.add(new Shop("Toko Samsul Rahadian", false));
        listShop.add(new Shop("Bubuk Kopi Bang Samsul", false));
        rvShopResult = rootView.findViewById(R.id.rvShopResult);
        rvShopResult.setNestedScrollingEnabled(false);
        shopAdapter = new SearchShopResultAdapter(getContext(),listShop, mListener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        rvShopResult.setLayoutManager(layoutManager);
        rvShopResult.setAdapter(shopAdapter);
    }

    public void goTo(View v) {
        Fragment fragment;
        switch (v.getId()){
            case R.id.tvLoadMoreProduct:
                fragment = new SearchProductFragment();
                switchToFragment(fragment);
                break;
        }
    }

    private void switchToFragment(Fragment fragment){
        Bundle args = new Bundle();
        args.putString("Search", etSearch.getText().toString());
        fragment.setArguments(args);

        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction();
    }
}
