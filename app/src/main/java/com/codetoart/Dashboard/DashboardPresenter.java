package com.codetoart.Dashboard;


import com.codetoart.interfaces.ImdbApiInterface;
import com.codetoart.models.MovieData;
import com.codetoart.utils.Utils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chetan_g on 23/11/17.
 */

class DashboardPresenter {
    private ImdbApiInterface mLocationApiInterface;
    private DashboardActivity view;
    DashboardPresenter(DashboardActivity view, ImdbApiInterface mLocationApiInterface) {
        this.view = view;
        this.mLocationApiInterface = mLocationApiInterface;
    }
    void removeView(){
        view = null;
    }

    void inflateList(String apiKey) {
        Utils.showLog("getLocationData", "getting location...");
        mLocationApiInterface.getLocationData(apiKey).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieData movieData) {
                        view.showItemList(movieData.getResults());
                        Utils.showLog("getLocationData", "Data "+movieData);
                    }
                });
    }

}
