package com.codetoart.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.codetoart.BuildConfig;
import com.codetoart.CodeToArtApplication;
import com.codetoart.InformationActivity;
import com.codetoart.R;
import com.codetoart.interfaces.ImdbApiInterface;
import com.codetoart.models.Results;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements DashboardContract {
    @BindView(R.id.list_item) RecyclerView mTripList;
    private DashboardPresenter mDashboardPresenter;
    private ItemListAdapter mAdapter;
    @Inject
    ImdbApiInterface mLocationApiInterface;
    private static final String API_KEY = "b7cd3340a794e5a2f35e3abb820b497f";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((CodeToArtApplication) getApplication())
                .getmLocationComponent().inject(this);
        mDashboardPresenter = new DashboardPresenter(this, mLocationApiInterface);
        intializeItemListRecycleView();
        mDashboardPresenter.inflateList(API_KEY);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(this, InformationActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Initialize recyclerview
     */
    private void intializeItemListRecycleView() {
        mTripList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(false);
        layoutManager.setStackFromEnd(true);
        mTripList.setLayoutManager(layoutManager);
    }


    /**
     * Inflate recyclerview
     * @param items
     */
    @Override
    public void showItemList(Results[] items) {
        mAdapter = new ItemListAdapter(DashboardActivity.this, items);
        mTripList.setAdapter(mAdapter);
        mTripList.scrollToPosition(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDashboardPresenter.removeView();
    }
}
