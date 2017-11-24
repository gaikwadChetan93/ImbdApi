package com.codetoart.di.modules;

import com.codetoart.di.scopes.UserScope;
import com.codetoart.interfaces.ImdbApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class LocationModule {

    @Provides
    @UserScope // needs to be consistent with the component scope
    public ImdbApiInterface providesLocationInterface(Retrofit retrofit) {
        return retrofit.create(ImdbApiInterface.class);
    }
}