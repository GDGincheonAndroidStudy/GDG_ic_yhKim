package com.example.young.gdg_yhkim;

import android.os.AsyncTask;

/**
 * Created by apple on 2016-01-15.
 */
public class OpenWeatherAPITask extends AsyncTask<String, Void, Weather> {

    @Override
    public Weather doInBackground(String... params) {
        OpenWeatherAPIClient client = new OpenWeatherAPIClient();

        String strCity = params[0];


        // API 호출
        Weather w = client.getWeather(strCity);


        return w;
    }


}
