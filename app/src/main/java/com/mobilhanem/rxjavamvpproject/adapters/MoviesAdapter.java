package com.mobilhanem.rxjavamvpproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobilhanem.rxjavamvpproject.R;
import com.mobilhanem.rxjavamvpproject.model.Weather;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder>  {

    List<Weather> weatherList;
    Context context;

    public MoviesAdapter(List<Weather> weatherList, Context context) {
        this.weatherList = weatherList;
        this.context = context;
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        MoviesHolder moviesHolder = new MoviesHolder(v);
        return moviesHolder;
    }

    @Override
    public void onBindViewHolder(MoviesHolder moviesHolder, int position) {

        moviesHolder.movieName.setText(weatherList.get(position).getTitle());
        Glide.with(context).load(weatherList.get(position).getImage()).apply(new RequestOptions()
                .centerCrop()).into(moviesHolder.movieImage);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class MoviesHolder extends RecyclerView.ViewHolder {

        TextView movieName;
        ImageView movieImage;

        public MoviesHolder(View view) {
            super(view);
            movieName =  view.findViewById(R.id.movieTxt);
            movieImage = view.findViewById(R.id.movieIcon);
        }
    }
}
