package com.codetoart.interfaces;


import com.codetoart.models.AllImages;
import com.codetoart.models.MovieData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ImdbApiInterface {
    @GET("3/movie/upcoming")
    Observable<MovieData> getLocationData(@Query("api_key") String apiKey);

    @GET("3/movie/{movie-id}")
    Observable<MovieData> getMovieDetails(@Path("movie-id") String movie_id
            ,@Query("api_key") String apiKey);

    @GET("3/movie/{movie-id}/images")
    Observable<AllImages> getMovieImages(@Path("movie-id") String movie_id
            , @Query("api_key") String apiKey);
}