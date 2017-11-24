package com.codetoart.di.components;

import com.codetoart.di.modules.LocationModule;
import com.codetoart.di.scopes.UserScope;
import com.codetoart.Dashboard.DashboardActivity;
import com.codetoart.MovieDetails.MovieDetailsActivity;

import dagger.Component;


@UserScope // using the previously defined scope, note that @Singleton will not work
@Component(dependencies = NetComponent.class, modules = LocationModule.class)
public interface LocationComponent {
    void inject(DashboardActivity activity);

    void inject(MovieDetailsActivity navigationActivity);
}