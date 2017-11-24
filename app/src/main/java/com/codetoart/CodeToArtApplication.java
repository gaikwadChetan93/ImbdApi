package com.codetoart;

import android.app.Application;

import com.codetoart.di.components.DaggerLocationComponent;
import com.codetoart.di.components.DaggerNetComponent;
import com.codetoart.di.components.LocationComponent;
import com.codetoart.di.components.NetComponent;
import com.codetoart.di.modules.AppModule;
import com.codetoart.di.modules.LocationModule;
import com.codetoart.di.modules.NetModule;

/**
 * Created by chetan on 23-Nov-17.
 */

public class CodeToArtApplication extends Application {
    private NetComponent mNetComponent;
    private LocationComponent mLocationComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule(BuildConfig.BASE_URL))
                .build();

        mLocationComponent = DaggerLocationComponent.builder()
                .netComponent(mNetComponent)
                .locationModule(new LocationModule())
                .build();
    }
    public LocationComponent getmLocationComponent() { return mLocationComponent; }
}
