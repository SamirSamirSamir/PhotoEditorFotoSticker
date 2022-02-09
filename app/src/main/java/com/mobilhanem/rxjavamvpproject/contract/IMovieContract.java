package com.mobilhanem.rxjavamvpproject.contract;

import com.mobilhanem.rxjavamvpproject.model.Weather;

import java.util.List;

public interface IMovieContract {

     interface View {

        void init();
        void showProgress();
        void hideProgress();
        void showError(String errorMsg);
        void loadList(List<Weather> weatherList);

    }

     interface Presenter {
        void start();
        void fetchMovies();
    }
}
