/**
 * @file WeatherListModel.java
 * @brief This is model class for list screen, it will handle all business logic.
 * @author Shrikant
 * @date 14/04/2018
 */

package com.mobilhanem.rxjavamvpproject.model;

import android.util.Log;

import com.mobilhanem.rxjavamvpproject.services.NetworkClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class WeatherListModel implements WeatherListContract.Model {

    private final String TAG = "WeatherListModel";

         @Override
    public void getMovieList(final OnFinishedListener onFinishedListener, int i) {

        Retrofit retrofit = NetworkClient.getRetrofitClient();

        WeatherAPIs weatherAPIs = retrofit.create(WeatherAPIs.class);
        Call <WResponse>call = weatherAPIs.getWeatherByCity("Baku", "ac56edb741d3b77a3ac69682d48c7483");

             call.enqueue(new Callback<WResponse>() {
                 @Override
                 public void onResponse(Call<WResponse> call, Response<WResponse> response) {
                     List<Main> movies = (List<Main>) response.body().getMain();
                     Log.d(TAG, "Number of movies received: " + movies.size());
                     onFinishedListener.onFinished(movies);
                 }

/*                    movieTxt.setText("Temp: " + wResponse.getMain().getTemp() + "\n " +
                            "Humidity: " + wResponse.getMain().getHumidity() + "\n" +
                            "Pressure: " + wResponse.getMain().getPressure());
                            */

        @Override
        public void onFailure(Call call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);

                }
                });

        }

}
