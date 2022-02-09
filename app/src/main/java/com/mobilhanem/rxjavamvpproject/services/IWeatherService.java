package com.mobilhanem.rxjavamvpproject.services;

import com.mobilhanem.rxjavamvpproject.model.Weather;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IWeatherService {
    @GET("json/movies.json")
    Observable<List<Weather>> getMovieList();
}
