package com.codetoart.Dashboard;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codetoart.R;
import com.codetoart.models.Results;
import com.codetoart.MovieDetails.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {


    private Results[] itemList;

    private DashboardActivity activity;
    private ViewHolder viewHolder;
    private final String imageUrlPrefix = "https://image.tmdb.org/t/p/w300/";
    ItemListAdapter(DashboardActivity activity, Results[] trips) {
        this.itemList = trips;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //inflate your layout and pass it to view holder
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.row_item_list, viewGroup, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder,final int position) {

        //setting data to view holder elements
        // populate row widgets from record data
        final Results item = itemList[position];

        viewHolder.tvItemName.setText(item.getTitle());
        viewHolder.tvDate.setText(item.getRelease_date());
        if(item.getAdult().equals("false")){
            viewHolder.tvMovieType.setText("U/A");
        } else {
            viewHolder.tvMovieType.setText("A");
        }
        Picasso.with(activity).load(imageUrlPrefix.concat(item.getPoster_path())).into(viewHolder.imgMovie);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, MovieDetailsActivity.class);
                intent.putExtra("data", item);
                activity.startActivity(intent);
            }
        });
        viewHolder.cardView.setTag(position);
    }




    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.length : 0);
    }

    /**
     * View holder to display each RecylerView item
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        // get widgets from the view
        @BindView(R.id.tv_title)
        TextView tvItemName;
        @BindView(R.id.img_movie)
        ImageView imgMovie;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_type)
        TextView tvMovieType;

        @BindView(R.id.card_view)
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}