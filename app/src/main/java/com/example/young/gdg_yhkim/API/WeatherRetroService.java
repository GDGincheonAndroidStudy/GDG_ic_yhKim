package com.example.young.gdg_yhkim.API;

import com.example.young.gdg_yhkim.Weather;

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
    Call<Weather> getCity(@Path("city") String city);
}
