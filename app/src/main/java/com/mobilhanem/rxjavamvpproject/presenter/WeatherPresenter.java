package com.mobilhanem.rxjavamvpproject.presenter;


import com.mobilhanem.rxjavamvpproject.Const;
import com.mobilhanem.rxjavamvpproject.contract.IMovieContract;
import com.mobilhanem.rxjavamvpproject.model.Weather;
import com.mobilhanem.rxjavamvpproject.services.IWeatherService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherPresenter implements IMovieContract.Presenter {

    IMovieContract.View mView;

    public WeatherPresenter(IMovieContract.View view)
    {
        this.mView = view;
    }

    @Override
    public void start() {

        mView.init();

    }

    @Override
    public void fetchMovies() {

        IWeatherService iMovieService = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(IWeatherService.class);

        iMovieService.getMovieList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Weather>>() {
                               @Override
                               public void onSubscribe(Disposable d) {

                                   mView.showProgress();
                               }

                               @Override
                               public void onNext(List<Weather> weatherList) {

                                   mView.hideProgress();
                                   mView.loadList(weatherList);
                               }

                               @Override
                               public void onError(Throwable e) {

                                   mView.hideProgress();
                                   mView.showError(e.toString());
                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }
}
