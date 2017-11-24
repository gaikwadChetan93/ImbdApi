package com.codetoart.MovieDetails;

import com.codetoart.interfaces.ImdbApiInterface;
import com.codetoart.models.AllImages;
import com.codetoart.utils.Utils;

import java.util.ArrayList;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chetan_g on 23/11/17.
 */

class MovieDetailsPresenter {

    private MovieDetailsActivity view;
    private ImdbApiInterface locationApiInterface;
    MovieDetailsPresenter(MovieDetailsActivity view, ImdbApiInterface locationApiInterface) {
        this.view = view;
        this.locationApiInterface = locationApiInterface;
    }
    void removeView(){
        view = null;
    }

    /**
     * Get location data from server
     */
    void getLocationData(String api, String movieId) {
        Utils.showLog("getLocationData", "getting location...");
        locationApiInterface.getMovieImages(movieId, api).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllImages>() {
                    @Override
                    public void onCompleted() {
                        Utils.showLog("getLocationData", "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.showLog("getLocationData", "error : " + e.getMessage());
                    }

                    @Override
                    public void onNext(AllImages images) {
                        Utils.showLog("getLocationData", "error : " + images.toString());
                        ArrayList<String> urls = new ArrayList<String>();
                        for (int i=0; i<5; i++){
                            urls.add(images.getPosters()[i].getFile_path());
                        }
                        view.showViewPager(urls);
                    }
                });
    }

    public void getImages(String id) {

    }
}
