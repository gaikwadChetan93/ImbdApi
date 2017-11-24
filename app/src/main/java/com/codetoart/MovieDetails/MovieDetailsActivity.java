package com.codetoart.MovieDetails;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codetoart.BuildConfig;
import com.codetoart.CodeToArtApplication;
import com.codetoart.R;
import com.codetoart.interfaces.ImdbApiInterface;
import com.codetoart.models.MovieData;
import com.codetoart.models.Results;
import com.codetoart.utils.Utils;
import com.itsronald.widget.ViewPagerIndicator;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chetan on 23/11/17.
 */
public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract {

    @Inject
    ImdbApiInterface locationApiInterface;
    @BindView(R.id.tv_title) TextView mTitle;
    @BindView(R.id.tv_overview) TextView mOverview;
    @BindView(R.id.ratingBar) RatingBar ratingBar;
    private MovieDetailsPresenter navigationPresenter;
    private static final String API_KEY = "b7cd3340a794e5a2f35e3abb820b497f";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        ((CodeToArtApplication) getApplication())
                .getmLocationComponent().inject(this);
        navigationPresenter = new MovieDetailsPresenter(this, locationApiInterface);

        Results results = getIntent().getParcelableExtra("data");
        if(results != null){
            mTitle.setText(results.getTitle());
            mOverview.setText(results.getOverview());
            navigationPresenter.getImages(results.getId());
            if(Utils.isConnectedToInternet(this)) {
                navigationPresenter.getLocationData(API_KEY,results.getId());
            } else {
                Toast.makeText(this, "Not connected to internet", Toast.LENGTH_SHORT).show();
            }
            ratingBar.setRating(Float.parseFloat(results.getVote_average())/2);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * Initialize viewpager
     * @param images
     */
    private void initializeViewPager(ArrayList<String> images) {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), images.size(),images));

        final ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewPager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;

        final ViewPagerIndicator indicator = new ViewPagerIndicator(this);
        indicator.setSelectedDotColor(ContextCompat.getColor(this, R.color.colorAccent));
        viewPager.addView(indicator, layoutParams);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        navigationPresenter.removeView();
    }

    @Override
    public void showData(MovieData locations) {

    }

    @Override
    public void showViewPager(ArrayList<String> images) {
        initializeViewPager(images);
    }
}
