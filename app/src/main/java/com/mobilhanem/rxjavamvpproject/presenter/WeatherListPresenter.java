/**
 * @file WeatherListPresenter.java
 * @brief This is a presentation class, handles the communication between list view and list model
 * @author Shrikant
 * @date 15/04/2018
 */
package com.mobilhanem.rxjavamvpproject.presenter;

import android.util.Log;

import com.mobilhanem.rxjavamvpproject.model.WeatherListContract;
import com.mobilhanem.rxjavamvpproject.model.WeatherListModel;
import com.mobilhanem.rxjavamvpproject.model.Main;

import java.util.List;

public class WeatherListPresenter implements WeatherListContract.Presenter, WeatherListContract.Model.OnFinishedListener {

    private WeatherListContract.View movieListView;

    private WeatherListContract.Model movieListModel;

    public WeatherListPresenter(WeatherListContract.View movieListView) {
        this.movieListView = movieListView;
        movieListModel = new WeatherListModel();
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }

    @Override
    public void getMoreData(int pageNo) {

        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this, 1);
    }

    @Override
    public void requestDataFromServer() {

        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this, 1);
    }

    @Override
    public void onFinished(List<Main> movieArrayList) {
        movieListView.setDataToRecyclerView(movieArrayList);
        Log.i("movieListView","movieListView");
        if (movieListView != null) {
            Log.i("movieListView","movieListView");
            movieListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {

        movieListView.onResponseFailure(t);
        if (movieListView != null) {
            movieListView.hideProgress();
        }
    }
}
