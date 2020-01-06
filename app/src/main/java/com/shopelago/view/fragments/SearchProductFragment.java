package com.shopelago.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.view.adapters.SearchProductAdapter;
import com.shopelago.models.Product;
import com.shopelago.models.Shop;
import com.shopelago.utils.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SearchProductFragment extends Fragment {

    // TODO: Customize parameter argument names
    // TODO: Customize parameters
    private MainActivity mainActivity;
    private View rootView;
    private Activity activity;
    private Context context;
    private Toolbar toolbarHome, toolbarSearch;
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
    private List<Product> listProduct;
    private RecyclerView rvProduct;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private SearchProductAdapter mAdapter;
    private EditText etSearch;
    private List<Product> filteredListProduct;
    private View tvEmptyResult;
    private ImageButton btnClearSearch;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SearchProductFragment() {
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

        rootView = inflater.inflate(R.layout.fragment_search_product, container, false);
        tvEmptyResult = rootView.findViewById(R.id.tvEmptyResult);

        toolbarSearch = mainActivity.findViewById(R.id.toolbar_search);
        etSearch = toolbarSearch.findViewById(R.id.etSearch);
        btnClearSearch = toolbarSearch.findViewById(R.id.btnClearSearch);

        btnClearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText("");
            }
        });

        String search = getArguments().getString("Search");
        etSearch.setText(search);
        if(search.length() == 0){
            hideClearSearch();
        } else {
            showClearSearch();
        }
        initProduct();
        filter(search);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etSearch.length() == 0){
                    hideClearSearch();
                } else {
                    showClearSearch();
                }
                Log.d("Search", "afterTextChanged: " + s.toString());
                String search = s.toString();
                filter(search);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }

    private void filter(String search){
        filteredListProduct = new ArrayList<>();
        for (Product p : listProduct){
            if(p.getName().toLowerCase().contains(search.toLowerCase())){
                filteredListProduct.add(p);
            }
        }
        mAdapter.setFilter(filteredListProduct, search);
        toogleEmptyProduct(filteredListProduct);
    }

    private void toogleEmptyProduct(List<Product> filteredList) {
        if(filteredList.size() < 1){
            rvProduct.setVisibility(View.GONE);
            tvEmptyResult.setVisibility(View.VISIBLE);
        } else {
            rvProduct.setVisibility(View.VISIBLE);
            tvEmptyResult.setVisibility(View.GONE);
        }
    }

    private void showClearSearch(){
        btnClearSearch.setVisibility(View.VISIBLE);
    }

    private void hideClearSearch(){
        btnClearSearch.setVisibility(View.GONE);
    }

    private void initProduct() {
        listProduct = new ArrayList<>();
        Product product = new Product("Samsung S8 - AUTHENTIC BNIB!", "8.500.000", "http://bhovdair.com/projects/shopelago/Products(7).jpg");
        product.setRating(5.0);
        Shop shop = new Shop("Samsung Official Store", true);
        shop.setLocation("Jakarta");
        product.setShop(shop);
        listProduct.add(product);

        product = new Product("Samsung J2 Prime with Bonus Antigores Front absolute black", "2.350.000", "http://bhovdair.com/projects/shopelago/Products(7).jpg");
        product.setRating(4.5);
        shop = new Shop("Samsung Official Store", true);
        shop.setLocation("Jakarta");
        product.setShop(shop);
        listProduct.add(product);

        product = new Product("Samsung Galaxy S10+ Brand New In Box (BNIB)!", "18.750.000", "http://bhovdair.com/projects/shopelago/Products(7).jpg");
        product.setRating(5.0);
        shop = new Shop("Samsung Official Store", true);
        shop.setLocation("Jakarta");
        product.setShop(shop);
        listProduct.add(product);

        product = new Product("Samsung S3 Mini - Bekas 4 bulan pakai", "950.000", "http://bhovdair.com/projects/shopelago/Products(7).jpg");
        product.setRating(3.0);
        shop = new Shop("Toko Barang Second", false);
        shop.setLocation("Cibubur");
        product.setShop(shop);
        listProduct.add(product);

        product = new Product("Xiaomi", "950.000", "http://bhovdair.com/projects/shopelago/Products(7).jpg");
        product.setRating(3.0);
        shop = new Shop("Toko Xiaomi", false);
        shop.setLocation("Bandung");
        product.setShop(shop);
        listProduct.add(product);

        rvProduct = rootView.findViewById(R.id.rvSearchProduct);
        int mNoOfColumns = RecyclerViewUtils.calculateNoOfColumns(getContext(), 200);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),mNoOfColumns);
        rvProduct.setNestedScrollingEnabled(false);
        mAdapter = new SearchProductAdapter(getContext(),listProduct, mListener);
        rvProduct.setLayoutManager(layoutManager);
        rvProduct.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarSearch = mainActivity.findViewById(R.id.toolbar_search);
        toolbarHome.setVisibility(View.GONE);
        toolbarSearch.setVisibility(View.VISIBLE);
    }
    @Override
    public void onStop() {
        super.onStop();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarSearch = mainActivity.findViewById(R.id.toolbar_search);
        toolbarHome.setVisibility(View.VISIBLE);
        toolbarSearch.setVisibility(View.GONE);
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
