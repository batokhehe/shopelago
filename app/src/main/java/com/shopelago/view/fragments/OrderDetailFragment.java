package com.shopelago.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.view.adapters.OrderDetailAdapter;
import com.shopelago.models.OrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class OrderDetailFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<OrderDetail> orderDetails;
    private MainActivity mainActivity;
    private View rootView;
    private Activity activity;
    private Context context;
    private Toolbar toolbarHome, toolbarFilter;
    private RecyclerView rvOrderDetail;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OrderDetailFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static OrderDetailFragment newInstance(int columnCount) {
        OrderDetailFragment fragment = new OrderDetailFragment();
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
        rootView = inflater.inflate(R.layout.fragment_order_detail, container, false);

        initOrderDetailList();
        return rootView;
    }

    private void initOrderDetailList() {
        orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail("Griplock Nankai Asli", "73.200", "https://ecs7.tokopedia.net/img/cache/700/product-1/2019/2/5/5150672/5150672_fa393227-bb21-44d5-9181-54becc012607_1000_1000.jpg", "1", "250", "Gram"));
        orderDetails.add(new OrderDetail("Olay Anti Aging Krim Pagi SPF15-50gr", "125.450", "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/UK-0022371/olay_olay-anti-aging---pelembab-total-effects-day-cream-normal-spf-15-7-in-1---50gr_full02.jpg", "1", "100", "Gram"));
        orderDetails.add(new OrderDetail("Makanan Kucing Felibite", "150.000", "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//93/MTA-2591364/felibite_felibite-makanan-kucing--20-kg-_full02.jpg", "1", "1", "Kg"));

        rvOrderDetail = (RecyclerView) rootView.findViewById(R.id.rvOrderDetail);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvOrderDetail.setNestedScrollingEnabled(false);
        rvOrderDetail.setLayoutManager(layoutManager);

        OrderDetailAdapter adapter = new OrderDetailAdapter(getContext(), orderDetails, mListener);
        rvOrderDetail.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarFilter = mainActivity.findViewById(R.id.toolbar_filter);
        toolbarHome.setVisibility(View.GONE);
        toolbarFilter.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarFilter = mainActivity.findViewById(R.id.toolbar_filter);
        toolbarHome.setVisibility(View.VISIBLE);
        toolbarFilter.setVisibility(View.GONE);
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
        void onListFragmentInteraction(OrderDetail item);
    }
}
