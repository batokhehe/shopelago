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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.view.adapters.ColorAdapter;
import com.shopelago.models.Color;
import com.shopelago.models.Product;
import com.shopelago.utils.RecyclerViewUtils;
import com.shopelago.viewmodels.ProductViewViewModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class ProductViewFragment extends Fragment {

    private ProductViewViewModel mViewModel;
    private List<Product> products;
    private View rootView;
    private CarouselView vCarousel;
    private List<Color> colors;
    private RecyclerView rvColor;
    private Context context;
    private Activity activity;
    private MainActivity mainActivity;
    private Button btnAddtoCart, btnBuyNow;
    private RelativeLayout rlLoadMore;
    private boolean isMoreShow = false;
    private boolean isReadShow = false;
    private ImageButton btnLoadMore;
    private TextView tvReadMore, tvProductDesc;

    public static ProductViewFragment newInstance() {
        return new ProductViewFragment();
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

        rootView = inflater.inflate(R.layout.fragment_product_view, container, false);

        initComponent();
        initProductImage();
        initProductColor();

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(btnAddtoCart);
            }
        });

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(btnBuyNow);
            }
        });

        rlLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadMore(isMoreShow);
            }
        });

        tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadMore(isReadShow);
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

    private void initProductImage() {
        products = new ArrayList<>();
        products.add(new Product("Samsung S8 - AUTHENTIC BNIB", "8.500.000", "https://cdns.klimg.com/merdeka.com/i/w/news/2017/05/02/838892/670x335/6-tips-ampuh-untuk-menghemat-baterai-di-samsung-galaxy-s8s8.jpg"));
        products.add(new Product("Samsung S8 - AUTHENTIC BNIB", "8.500.000", "https://cdns.klimg.com/merdeka.com/i/w/news/2017/05/02/838892/670x335/6-tips-ampuh-untuk-menghemat-baterai-di-samsung-galaxy-s8s8.jpg"));
        products.add(new Product("Samsung S8 - AUTHENTIC BNIB", "8.500.000", "https://cdns.klimg.com/merdeka.com/i/w/news/2017/05/02/838892/670x335/6-tips-ampuh-untuk-menghemat-baterai-di-samsung-galaxy-s8s8.jpg"));

        vCarousel.setPageCount(products.size());
        vCarousel.setImageListener(imageListener);
    }

    private void initProductColor() {
        colors = new ArrayList<>();
        colors.add(new Color("Metalic Blue", "#0139D8"));
        colors.add(new Color("Modern Red", "#CE0000"));
        colors.add(new Color("Yellow", "#FEC60F"));
        colors.add(new Color("Steel Gray", "#8C8C8C"));
        colors.add(new Color("Silver", "#D1D1D1"));
        colors.add(new Color("Violet", "#550055"));

        int mNoOfColumns = RecyclerViewUtils.calculateNoOfColumns(getContext(), 120);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),mNoOfColumns);
        rvColor.setNestedScrollingEnabled(false);
        rvColor.setLayoutManager(layoutManager);

        ColorAdapter adapter = new ColorAdapter(getContext(),colors);
        rvColor.setAdapter(adapter);
    }

    private void initComponent(){
        vCarousel = (CarouselView) rootView.findViewById(R.id.carouselProductView);
        rvColor = (RecyclerView) rootView.findViewById(R.id.rvProductColor);
        btnAddtoCart = (Button) rootView.findViewById(R.id.btnAddToCart);
        btnBuyNow = (Button) rootView.findViewById(R.id.btnBuyNow);

        btnLoadMore = (ImageButton) rootView.findViewById(R.id.btnLoadMore);
        rlLoadMore = (RelativeLayout) rootView.findViewById(R.id.rlLoadMore);
        tvReadMore = (TextView) rootView.findViewById(R.id.tvReadMore);
        tvProductDesc = (TextView) rootView.findViewById(R.id.tvProductDesc);
    }

    private void LoadMore(boolean show){
        int rotation = show ? 90 : -90;
        int visible = show ? View.GONE : View.VISIBLE;
        btnLoadMore.setRotation(btnLoadMore.getRotation() + rotation);
        rvColor.setVisibility(visible);
        isMoreShow = show ? false : true;
    }

    private void ReadMore(boolean show){
        int maxLines = show ? 1000 : 4;
        tvProductDesc.setMaxLines(maxLines);
        isReadShow = show ? false : true;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(sampleImages[position]);
            Glide.with(context).load(products.get(position).getImage()).fitCenter().into(imageView);
        }
    };

    public void goTo(View v) {
        Fragment fragment;
        switch (v.getId()){
            case R.id.btnAddToCart:
                fragment = new AddtoCartFragment();
                switchToFragment(fragment);
                break;
            case R.id.btnBuyNow:
                fragment = new BuyNowFragment();
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
}
