package com.codetoart.di.components;

import com.codetoart.di.modules.AppModule;
import com.codetoart.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
   Retrofit retrofit();
}