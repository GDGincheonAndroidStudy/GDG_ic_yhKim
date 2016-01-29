package com.example.young.gdg_yhkim;

import com.example.young.gdg_yhkim.Model.WeatherListData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NetworkRetrofit {
    final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    final String APP_KEY = "cdc07c15d3d72285fb498d47c967c603";

    public void request(Callback<WeatherListData> callback, String strCity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FORECAST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherListData> call = service.getWeatherListData(strCity,APP_KEY);
        call.enqueue(callback);
    }

    public interface WeatherService {

        @GET("weather")
        Call<WeatherListData> getWeatherListData(
                @Query("q") String city,
                @Query("appid") String appid);
    }
}
