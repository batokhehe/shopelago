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
import android.widget.TextView;
import android.widget.Toast;

import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.view.adapters.PurchaseListAdapter;
import com.shopelago.models.PurchaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PurchaseListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<PurchaseList> purchaseLists;
    private MainActivity mainActivity;
    private View rootView;
    private Activity activity;
    private Context context;
    private Toolbar toolbarHome, toolbarDefault;
    private RecyclerView rvPurchasingList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PurchaseListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PurchaseListFragment newInstance(int columnCount) {
        PurchaseListFragment fragment = new PurchaseListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        context = getContext();
        mainActivity = (MainActivity) getActivity();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_purchase_list, container, false);

        initPurchasingList();
        return rootView;
    }

    private void initPurchasingList() {
        purchaseLists = new ArrayList<>();
        purchaseLists.add(new PurchaseList("2", "28 Maret 2019", "INV/20192804/XII/V/128941795", "https://ecs7.tokopedia.net/img/cache/700/product-1/2019/2/5/5150672/5150672_fa393227-bb21-44d5-9181-54becc012607_1000_1000.jpg", "Griplock Nankai Asli", "5", "419.000"));
        purchaseLists.add(new PurchaseList("1", "03 Jan 2019", "INV/20190103/XIII/IX/23896733", "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/UK-0022371/olay_olay-anti-aging---pelembab-total-effects-day-cream-normal-spf-15-7-in-1---50gr_full02.jpg", "Olay Anti Aging Krim Pagi SPF15-50gr", "1", "125.450"));
        purchaseLists.add(new PurchaseList("3", "01 Jan 2019", "INV/20192804/XII/V/128941795", "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//93/MTA-2591364/felibite_felibite-makanan-kucing--20-kg-_full02.jpg", "Makanan Kucing Felibite", "2", "275.000"));

        rvPurchasingList = (RecyclerView) rootView.findViewById(R.id.rvPurchasingList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        rvPurchasingList.setNestedScrollingEnabled(false);
        rvPurchasingList.setLayoutManager(layoutManager);

        PurchaseListAdapter adapter = new PurchaseListAdapter(context, purchaseLists);
        rvPurchasingList.setAdapter(adapter);
        adapter.setOnClickListener(new PurchaseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PurchaseList purchaseList) {
                Toast.makeText(context, "Product : " + purchaseList.getName(), Toast.LENGTH_SHORT).show();
                OrderDetailFragment fragment = new OrderDetailFragment();
//                Bundle args = new Bundle();
//                args.putInt("id", product.getId());
//                fragment.setArguments(args);
                getFragmentManager().beginTransaction().
                        replace(R.id.content, fragment).
                        addToBackStack(null).
                        commit();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarDefault = mainActivity.findViewById(R.id.toolbar_default);
        toolbarHome.setVisibility(View.GONE);
        toolbarDefault.setVisibility(View.VISIBLE);
        TextView tvTitle = toolbarDefault.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.title_purchase_list);
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
        void onListFragmentInteraction(PurchaseList item);
    }
}
