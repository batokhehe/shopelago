package com.shopelago.view.fragments;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.view.adapters.ColorAdapter;
import com.shopelago.models.Color;
import com.shopelago.models.Product;
import com.shopelago.utils.RecyclerViewUtils;
import com.shopelago.viewmodels.ProductViewViewModel;

import java.util.ArrayList;
import java.util.List;

public class BuyNowFragment extends Fragment {

    private ProductViewViewModel mViewModel;
    private List<Product> products;
    private View rootView;
    private List<Color> colors;
    private RecyclerView rvColor;
    private Context context;
    private Activity activity;
    private MainActivity mainActivity;
    private Button btnBuyNow, btnCancel;
    private Toolbar toolbarHome, toolbarDefault;
    private ImageView ivProductImage;
    private ImageButton btnIncrease, btnDecrease, btnLoadMore;
    private TextView tvQty, tvNotes;
    private EditText etNotes;
    private boolean isMoreShow = false;
    private boolean isNotesShow = false;
    private RelativeLayout rlLoadMore;

    public static BuyNowFragment newInstance() {
        return new BuyNowFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        context = getContext();
        activity = getActivity();
        mainActivity = (MainActivity) getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_buy_now, container, false);

        initComponent();
        initProductColor();
        initProductImage("data:image/jpeg;base64,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");

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Increase();
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Decrease();
            }
        });

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(btnBuyNow);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(btnCancel);
            }
        });

        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadMore(isMoreShow);
            }
        });

        rlLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadMore(isMoreShow);
            }
        });

        tvNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowNotes(isNotesShow);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductViewViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initProductColor() {
        colors = new ArrayList<>();
        colors.add(new Color("Metalic Blue", "#0139D8"));
        colors.add(new Color("Modern Red", "#CE0000"));
        colors.add(new Color("Yellow", "#FEC60F"));
        colors.add(new Color("Steel Gray", "#8C8C8C"));
        colors.add(new Color("Silver", "#D1D1D1"));
        colors.add(new Color("Violet", "#550055"));

        int mNoOfColumns = RecyclerViewUtils.calculateNoOfColumns(context, 120);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),mNoOfColumns);
        rvColor.setNestedScrollingEnabled(false);
        rvColor.setLayoutManager(layoutManager);

        ColorAdapter adapter = new ColorAdapter(getContext(),colors);
        rvColor.setAdapter(adapter);
    }

    private void initComponent(){
        rvColor = (RecyclerView) rootView.findViewById(R.id.rvProductColor);
        ivProductImage = (ImageView) rootView.findViewById(R.id.ivProductImage);
        tvQty = (TextView) rootView.findViewById(R.id.tvQty);
        btnIncrease = (ImageButton) rootView.findViewById(R.id.btnIncrease);
        btnDecrease = (ImageButton) rootView.findViewById(R.id.btnDecrease);
        btnLoadMore = (ImageButton) rootView.findViewById(R.id.btnLoadMore);
        rlLoadMore = (RelativeLayout) rootView.findViewById(R.id.rlLoadMore);
        tvNotes = (TextView) rootView.findViewById(R.id.tvNotes);
        etNotes = (EditText) rootView.findViewById(R.id.etNotes);
        btnBuyNow = (Button) rootView.findViewById(R.id.btnBuyNow);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
    }

    private void Increase(){
        int value = Integer.parseInt(tvQty.getText().toString());
        value++;
        tvQty.setText(String.valueOf(value));
    }

    private void Decrease(){
        int value = Integer.parseInt(tvQty.getText().toString());
        value--;
        tvQty.setText(String.valueOf(value));
    }

    private void LoadMore(boolean show){
        int rotation = show ? 90 : -90;
        int visible = show ? View.GONE : View.VISIBLE;
        btnLoadMore.setRotation(btnLoadMore.getRotation() + rotation);
        rvColor.setVisibility(visible);
        isMoreShow = show ? false : true;
    }

    private void ShowNotes(boolean show){
        int visible = show ? View.GONE : View.VISIBLE;
        etNotes.setVisibility(visible);
        isNotesShow = show ? false : true;
    }

    private void ShowDialog(){
        Toast.makeText(context, getResources().getString(R.string.buy_now), Toast.LENGTH_SHORT).show();
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.modal_atc_success);
//        dialog.show();
//
//        // Hide after some seconds
//        final Handler handler  = new Handler();
//        final Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                if (dialog.isShowing()) {
//                    dialog.dismiss();
//                }
//            }
//        };
//
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                handler.removeCallbacks(runnable);
//            }
//        });
//
//        handler.postDelayed(runnable, 3000);
    }


    private void initProductImage(String url){
        Glide.with(context).load(url).fitCenter().into(ivProductImage);
    }

    public void goTo(View v) {
        Fragment fragment;
        switch (v.getId()){
            case R.id.btnBuyNow:
                ShowDialog();
                break;
            case R.id.btnCancel:
                mainActivity.getSupportFragmentManager().popBackStack();
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
    public void onResume() {
        super.onResume();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarDefault = mainActivity.findViewById(R.id.toolbar_default);
        toolbarHome.setVisibility(View.GONE);
        toolbarDefault.setVisibility(View.VISIBLE);
        TextView tvTitle = toolbarDefault.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.title_buy_product);
    }
    @Override
    public void onStop() {
        super.onStop();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarDefault = mainActivity.findViewById(R.id.toolbar_default);
        toolbarHome.setVisibility(View.VISIBLE);
        toolbarDefault.setVisibility(View.GONE);
    }
}
