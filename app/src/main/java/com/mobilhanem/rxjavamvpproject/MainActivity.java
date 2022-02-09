package com.mobilhanem.rxjavamvpproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilhanem.rxjavamvpproject.adapters.MoviesAdapter;
import com.mobilhanem.rxjavamvpproject.contract.IMovieContract;
import com.mobilhanem.rxjavamvpproject.model.Weather;
import com.mobilhanem.rxjavamvpproject.model.WeatherListContract;
import com.mobilhanem.rxjavamvpproject.presenter.WeatherListPresenter;
import com.mobilhanem.rxjavamvpproject.presenter.WeatherPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMovieContract.View {

    private WeatherListPresenter weatherListPresenter;
    private RecyclerView rvMovieList;
    private List<Weather> moviesList;
    private MoviesAdapter moviesAdapter;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private TextView movieTxt;
    private WeatherPresenter weatherPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        weatherPresenter = new WeatherPresenter(MainActivity.this);
  //      weatherPresenter.start();
        weatherListPresenter = new WeatherListPresenter((WeatherListContract.View) this);

    }

    @Override
    public void init() {

        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressBar);
        movieTxt = findViewById(R.id.movieTxt);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //weatherPresenter.fetchMovies();
        //fetchWeatherDetails();

    }


    @Override
    public void showProgress() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {

        if(progressBar!=null && progressBar.isShown()){
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void showError(String errorMsg) {

        Toast.makeText(MainActivity.this, ""+errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadList(List<Weather> weatherList) {

        moviesAdapter = new MoviesAdapter(weatherList,getApplicationContext());
        recyclerView.setAdapter(moviesAdapter);
    }
}
