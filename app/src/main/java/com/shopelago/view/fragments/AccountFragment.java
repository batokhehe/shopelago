package com.shopelago.view.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonElement;
import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.ShopelagoApplication;
import com.shopelago.config.ApiResponse;
import com.shopelago.config.HawkHelper;
import com.shopelago.factory.AccountViewModelFactory;
import com.shopelago.models.Response;
import com.shopelago.models.requests.apply_premium.ApplyPremium;
import com.shopelago.models.requests.login.VerifyLogin;
import com.shopelago.utils.Constant;
import com.shopelago.utils.Responses;
import com.shopelago.view.activities.SignInActivity;
import com.shopelago.viewmodels.AccountViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AccountFragment extends Fragment {
    @Inject
    AccountViewModelFactory viewModelFactory;
    @BindView(R.id.tvPurchaseList)
    TextView tvPurchaseList;
    @BindView(R.id.ivProfilePicture)
    ImageView ivProfilePicture;

    // TODO: Customize parameter argument names
    // TODO: Customize parameters
    private AccountViewModel viewModel;
    private Dialog dialog;
    private MainActivity mainActivity;
    private View rootView;
    private Activity activity;
    private Context context;
    private Toolbar toolbarHome, toolbarAccount;
    private ApplyPremium model;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AccountFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, rootView);
        initDialog();
        ((ShopelagoApplication) activity.getApplication()).getAppComponent().doAccountInjection(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AccountViewModel.class);
        viewModel.response().observe(this, this::consumeResponse);

        String imgPath = "http://bhovdair.com/projects/shopelago/profile_picture.jpg";
        Glide.with(getContext())
                .load(imgPath)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfilePicture);

        tvPurchaseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(view);
            }
        });

        hideKeyboard(context);

        return rootView;
    }

    private void initDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.modal_loading);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    private boolean isValid() {
        return true;
    }

    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                dialog.show();
                break;
            case SUCCESS:
                dialog.dismiss();
                renderSuccessResponse(apiResponse.data);
                break;
            case ERROR:
                dialog.dismiss();
                Toast.makeText(context, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }/*
     * method to handle success response
     * */
    private void renderSuccessResponse(JsonElement response) {
        if (response != null) {
            Response model = Responses.parser(response);
//            if (model.getResponse()){
//            } else {
            showModal();
//            }
        } else {
            Toast.makeText(context, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }

    private void showModal(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.modal_apply_premium_success);
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        // Hide after some seconds
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        };

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);
    }

    @OnClick(R.id.btnUpgradePremium)
    public void ApplyPremium() {
        if(isValid()){
            if (!Constant.checkInternetConnection(context)) {
                Toast.makeText(context, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            } else {
                String token = HawkHelper.GetUser().getToken();
                viewModel.hitApplyPremium(token);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarAccount = mainActivity.findViewById(R.id.toolbar_account);
        toolbarHome.setVisibility(View.GONE);
        toolbarAccount.setVisibility(View.VISIBLE);
    }
    @Override
    public void onStop() {
        super.onStop();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarAccount = mainActivity.findViewById(R.id.toolbar_account);
        toolbarHome.setVisibility(View.VISIBLE);
        toolbarAccount.setVisibility(View.GONE);
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

    public void goTo(View v) {
        Fragment fragment;
        switch (v.getId()){
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
