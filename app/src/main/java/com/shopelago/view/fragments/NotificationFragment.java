package com.shopelago.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shopelago.MainActivity;
import com.shopelago.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NotificationFragment extends Fragment {

    // TODO: Customize parameter argument names
    // TODO: Customize parameters
    private MainActivity mainActivity;
    private View rootView;
    private Activity activity;
    private Context context;
    private Toolbar toolbar;
    private Toolbar toolbarHome, toolbarDefault;
    private LinearLayout lsUnread;
    private TextView tvInbox, tvSent, tvWaitingPayment, tvWaitingConfirmation, tvOrderProcessed, tvOrderSent, tvOrderArrived, tvPurchaseList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotificationFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        context = getContext();
        mainActivity = (MainActivity) getActivity();
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        lsUnread = rootView.findViewById(R.id.lsUnread);
        tvInbox = rootView.findViewById(R.id.tvInbox);
        tvSent = rootView.findViewById(R.id.tvSent);
        tvWaitingPayment = rootView.findViewById(R.id.tvWaitingPayment);
        tvWaitingConfirmation = rootView.findViewById(R.id.tvWaitingConfirmation);
        tvOrderProcessed = rootView.findViewById(R.id.tvOrderProcessed);
        tvOrderSent = rootView.findViewById(R.id.tvOrderSent);
        tvOrderArrived = rootView.findViewById(R.id.tvOrderArrived);
        tvPurchaseList = rootView.findViewById(R.id.tvPurchaseList);

        lsUnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });
        tvInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });
        tvSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });
        tvWaitingPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });
        tvWaitingConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });
        tvOrderProcessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });
        tvOrderSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });
        tvOrderArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });
        tvPurchaseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarDefault = mainActivity.findViewById(R.id.toolbar_default);
        toolbarHome.setVisibility(View.GONE);
        toolbarDefault.setVisibility(View.VISIBLE);
        TextView tvTitle = toolbarDefault.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.title_notifications);
    }
    @Override
    public void onStop() {
        super.onStop();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarDefault = mainActivity.findViewById(R.id.toolbar_default);
        toolbarHome.setVisibility(View.VISIBLE);
        toolbarDefault.setVisibility(View.GONE);
    }


    public void goTo(View v) {
        Fragment fragment;
        switch (v.getId()){
            case R.id.lsUnread:
                break;
            case R.id.tvInbox:
                fragment = new MessageInboxFragment();
                switchToFragment(fragment);
                break;
            case R.id.tvSent:
                fragment = new MessageSentFragment();
                switchToFragment(fragment);
                break;
            case R.id.tvWaitingPayment:
                fragment = new WaitingPaymentFragment();
                switchToFragment(fragment);
                break;
            case R.id.tvWaitingConfirmation:
                break;
            case R.id.tvOrderProcessed:
                break;
            case R.id.tvOrderSent:
                break;
            case R.id.tvOrderArrived:
                break;
            case R.id.tvPurchaseList:
                fragment = new PurchaseListFragment();
                switchToFragment(fragment);
                break;
        }
    }

    private void switchToFragment(Fragment fragment){
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.addToBackStack(null);
        ft.commit();
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
