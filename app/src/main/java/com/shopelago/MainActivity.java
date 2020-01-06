package com.shopelago;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.shopelago.view.fragments.AccountFragment;
import com.shopelago.view.fragments.HomeFragment;
import com.shopelago.view.fragments.NotificationFragment;
import com.shopelago.view.fragments.SearchFragment;
import com.shopelago.view.fragments.WishlistFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private Toolbar toolbarDefault, toolbarHome, toolbarAccount, toolbarFilter, toolbarSearch;
    private ImageButton btnAccount, btnNotification, btnBackAccount, btnAccountSettings, btnBack, btnClearSearch, btnBackFilter, btnFilter, btnBackSearch;
    private EditText etSearch;
    private boolean openSearch = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            etSearch.setText("");
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    switchToFragment(fragment);
                    return true;
                case R.id.navigation_feeds:
                    return true;
                case R.id.navigation_messages:
                    return true;
                case R.id.navigation_wishlist:
                    fragment = new WishlistFragment();
                    switchToFragment(fragment);
                    return true;
                case R.id.navigation_cart:
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarHome = findViewById(R.id.toolbar_home);

        this.setSupportActionBar(toolbarHome);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToPrev();
            }
        });

        fragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.commit();

        initToolbar();

    }

    public void switchToFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void backToPrev(){
//        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
//            boolean done = getSupportFragmentManager().popBackStackImmediate();
//        }
        getSupportFragmentManager().popBackStack();
    }

    private void initToolbar(){
        etSearch = toolbarHome.findViewById(R.id.etSearch);
        btnAccount = toolbarHome.findViewById(R.id.btnAccount);
        toolbarAccount = findViewById(R.id.toolbar_account);
        btnNotification = toolbarHome.findViewById(R.id.btnNotification);
        btnBackAccount = toolbarAccount.findViewById(R.id.btnBackAccount);
        btnAccountSettings = toolbarAccount.findViewById(R.id.btnAccountSettings);

        toolbarFilter = findViewById(R.id.toolbar_filter);
        btnBackFilter = toolbarFilter.findViewById(R.id.btnBackFilter);
        btnFilter = toolbarFilter.findViewById(R.id.btnFilter);

        toolbarSearch = findViewById(R.id.toolbar_search);

        btnClearSearch = toolbarHome.findViewById(R.id.btnClearSearch);
        btnBackSearch = toolbarSearch.findViewById(R.id.btnBackSearch);


        etSearch.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(keyEvent != null) {
                    if (keyEvent.getAction() != KeyEvent.ACTION_DOWN)
                        return false;
                }
                return true;
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etSearch.length() == 0){
                    if(openSearch) {
                        hideClearSearch();
                    }
                } else {
                    if(!openSearch) {
                        showClearSearch();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnClearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText("");
            }
        });


        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new AccountFragment();
                switchToFragment(fragment);
            }
        });

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new NotificationFragment();
                switchToFragment(fragment);
            }
        });

        btnBackAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToPrev();
            }
        });

        btnAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Go to Settings", Toast.LENGTH_SHORT).show();
            }
        });

        btnBackFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToPrev();
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Go to Filter", Toast.LENGTH_SHORT).show();
            }
        });

        btnBackSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToPrev();
            }
        });
    }

    private void showClearSearch(){
        openSearch = true;
        openSearchFragment();
        btnClearSearch.setVisibility(View.VISIBLE);
    }

    private void hideClearSearch(){
        openSearch = false;
        btnClearSearch.setVisibility(View.GONE);
        backToPrev();
    }

    private void openSearchFragment(){
        fragment = new SearchFragment();
        switchToFragment(fragment);
    }


}
