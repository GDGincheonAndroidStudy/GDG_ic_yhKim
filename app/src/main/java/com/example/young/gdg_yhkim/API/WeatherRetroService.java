package com.example.young.gdg_yhkim.API;

import com.example.young.gdg_yhkim.Model.WeatherInfo;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by apple on 2016-01-22.
 */
public interface WeatherRetroService {
//    @GET("/weathers/{city}/weather")
//    List<Weather> listWeather(@Path("city") String city);

    @GET("/users/{city}")
    Call<WeatherInfo> getWeather(@Path("city") String city);
}
