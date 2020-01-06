package com.shopelago.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.view.adapters.WishlistAdapter;
import com.shopelago.models.Product;
import com.shopelago.utils.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class WishlistFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private MainActivity mainActivity;
    private View rootView;
    private Activity activity;
    private Context context;
    private Toolbar toolbarHome, toolbarDefault;
    private List<Product> products;
    private RecyclerView rvProduct;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WishlistFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static WishlistFragment newInstance(int columnCount) {
        WishlistFragment fragment = new WishlistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        context = getContext();
        mainActivity = (MainActivity) getActivity();
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wishlist, container, false);

        initWishlistProduct();

        return rootView;
    }

    private void initWishlistProduct() {
        products = new ArrayList<>();
        products.add(new Product("Olay Anti Aging Krim Pagi SPF15 - 50gr", "125.450", "http://bhovdair.com/projects/shopelago/Products(8).jpg"));
        products.add(new Product("Griplock Nankai Asli", "73.200", "http://bhovdair.com/projects/shopelago/Products(2).jpg"));
        products.add(new Product("Lorem Ipsumaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "11.500", "http://bhovdair.com/projects/shopelago/Products(3).jpg"));
        products.add(new Product("Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(4).jpg"));
        products.add(new Product("Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(5).jpg"));
        products.add(new Product("Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(6).jpg"));
        products.add(new Product("Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(7).jpg"));
        products.add(new Product("Lorem Ipsum", "11.500", "http://bhovdair.com/projects/shopelago/Products(1).jpg"));

        rvProduct = (RecyclerView) rootView.findViewById(R.id.rvWishlist);
        int mNoOfColumns = RecyclerViewUtils.calculateNoOfColumns(getContext(), 200);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),mNoOfColumns);
        rvProduct.setNestedScrollingEnabled(false);
        rvProduct.setLayoutManager(layoutManager);

        WishlistAdapter adapter = new WishlistAdapter(getContext(),products, mListener);
        rvProduct.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarDefault = mainActivity.findViewById(R.id.toolbar_default);
        toolbarHome.setVisibility(View.GONE);
        toolbarDefault.setVisibility(View.VISIBLE);
        TextView tvTitle = toolbarDefault.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.title_wishlist);
    }
    @Override
    public void onStop() {
        super.onStop();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarDefault = mainActivity.findViewById(R.id.toolbar_default);
        toolbarHome.setVisibility(View.VISIBLE);
        toolbarDefault.setVisibility(View.GONE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        void onListFragmentInteraction(Product item);
    }
}
