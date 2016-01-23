package com.example.young.gdg_yhkim;

import com.example.young.gdg_yhkim.API.WeatherRetroService;
import com.example.young.gdg_yhkim.Model.WeatherInfo;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by apple on 2016-01-15.
 */
public class OpenWeatherAPIClient_retro {
    final static String APP_ID = "cdc07c15d3d72285fb498d47c967c603";
    final static String openWeatherURL = "http://api.openweathermap.org/data/2.5/weather";

    public WeatherInfo getWeather(String strCity) {
        //retrofit 적용
     String urlString = openWeatherURL + "?q=" + strCity + "&appid=" + APP_ID;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlString)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


           // Call<BookListData> call = service.getBestSeller();
           // call.enqueue(callback);


        WeatherRetroService weatherService = retrofit.create(WeatherRetroService.class);
        Call<WeatherInfo> call = weatherService.getWeather(strCity);
         WeatherInfo  weatherData = null;


        //동기
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //비동기
        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Response<WeatherInfo> response) {
                WeatherInfo model = response.body();

                if (model==null) {
                    //404 or the response cannot be converted to City
                    ResponseBody responseBody = response.errorBody();
                    //error Exception
                    //Log.e();
                } else {
                    //200
//                    weatherData = {};
//                    return model;
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });


        return null;
    }


}


