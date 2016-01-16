package com.example.young.gdg_yhkim;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by apple on 2016-01-15.
 */
public class OpenWeatherAPITask extends AsyncTask<String, Void, ArrayList<Weather>> {

    @Override
    public ArrayList<Weather> doInBackground(String... params) {
        OpenWeatherAPIClient client = new OpenWeatherAPIClient();

        String strCity = params[0];


        // API 호출
        ArrayList<Weather> weatherArrayList = client.getWeather(strCity);

        Log.e("OpenWeatherAPITask", " " + weatherArrayList);

        return weatherArrayList;
    }
}
