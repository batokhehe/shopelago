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

import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.view.adapters.WaitingForPaymentAdapter;
import com.shopelago.models.WaitingForPayment;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class WaitingPaymentFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<WaitingForPayment> waitingForPayments;
    private MainActivity mainActivity;
    private View rootView;
    private Activity activity;
    private Context context;
    private Toolbar toolbarHome, toolbarDefault;
    private RecyclerView rvWaitingForPayment;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WaitingPaymentFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static WaitingPaymentFragment newInstance(int columnCount) {
        WaitingPaymentFragment fragment = new WaitingPaymentFragment();
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

        activity = getActivity();
        context = getContext();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_waiting_for_payment, container, false);

        initWaitingForOrderList();
        return rootView;
    }

    private void initWaitingForOrderList() {
        waitingForPayments = new ArrayList<>();
        waitingForPayments.add(new WaitingForPayment("7 May 2019", "325.500", "Transfer Bank - BNI", "(PT. Shopelago - 11937562735)"));
        waitingForPayments.add(new WaitingForPayment("6 May 2019", "1.124.350", "BCA Virtual Account", "(883572935957333)"));

        rvWaitingForPayment = (RecyclerView) rootView.findViewById(R.id.rvWaitingForPayment);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        rvWaitingForPayment.setNestedScrollingEnabled(false);
        rvWaitingForPayment.setLayoutManager(layoutManager);

        WaitingForPaymentAdapter adapter = new WaitingForPaymentAdapter(context, waitingForPayments, mListener, WaitingPaymentFragment.this);
        rvWaitingForPayment.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarDefault = mainActivity.findViewById(R.id.toolbar_default);
        toolbarHome.setVisibility(View.GONE);
        toolbarDefault.setVisibility(View.VISIBLE);
        TextView tvTitle = toolbarDefault.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.title_waiting_payment);
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
        void onListFragmentInteraction(WaitingForPayment item);
    }
}
