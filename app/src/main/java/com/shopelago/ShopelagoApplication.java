package com.shopelago;

import android.app.Application;
import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.shopelago.di.AppComponent;
import com.shopelago.di.AppModule;
import com.shopelago.di.DaggerAppComponent;
import com.shopelago.di.UtilsModule;

public class ShopelagoApplication extends Application {
    AppComponent appComponent;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ShopelagoApplication.context = getApplicationContext();
        Hawk.init(context).build();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule(ShopelagoApplication.context)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public static Context getAppContext() {
        return ShopelagoApplication.context;
    }
}
