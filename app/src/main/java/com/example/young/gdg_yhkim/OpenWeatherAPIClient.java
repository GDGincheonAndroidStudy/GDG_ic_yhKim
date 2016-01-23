package com.example.young.gdg_yhkim;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by apple on 2016-01-15.
 */
public class OpenWeatherAPIClient {
    final static String APP_ID = "cdc07c15d3d72285fb498d47c967c603";
    final static String openWeatherURL = "http://api.openweathermap.org/data/2.5/weather";

    public Weather getWeather(String strCity) {
        //retrofit 적용
  /*      String urlString = openWeatherURL + "?q=" + strCity + "&appid=" + APP_ID;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlString)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherRetroService weatherService = retrofit.create(WeatherRetroService.class);
        Call call = weatherService.getCity(strCity);
        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Response<WeatherInfo> response) {
                WeatherInfo model = response.body();

                if (model==null) {
                    //404 or the response cannot be converted to City
                    ResponseBody responseBody = response.errorBody();
                    //error Exception
                } else {
                    //200
                    return model;
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
        return null;*/

        //기존방식
        Weather w = new Weather();
        String urlString = openWeatherURL + "?q=" + strCity + "&appid=" + APP_ID;

        try {
            // call API by using HTTPURLConnection
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            // parse JSON
            w = parseJSON(json);


        } catch (MalformedURLException e) {
            System.err.println("Malformed URL");
            e.printStackTrace();
            return null;

        } catch (JSONException e) {
            System.err.println("JSON parsing error");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("URL Connection failed");
            e.printStackTrace();
            return null;
        }

        // set Weather Object

        return w;
    }

    private Weather parseJSON(JSONObject json) throws JSONException {
        Weather w = new Weather();
        int temp = json.getJSONObject("main").getInt("temp");

        System.out.println("Temp :" + json.getJSONObject("main").getInt("temp"));


        w.setTemprature(json.getJSONObject("main").getInt("temp"));
        w.setCity(json.getString("name"));
        w.setHumidity(json.getJSONObject("main").getInt("humidity"));
        w.setPres(json.getJSONObject("main").getInt("pressure"));
        w.setIon(json.getJSONObject("coord").getInt("lon"));
        w.setLat(json.getJSONObject("coord").getInt("lat"));
        w.setTempMax(json.getJSONObject("main").getInt("temp_max"));
        w.setTempMin(json.getJSONObject("main").getInt("temp_min"));

        //w.setCloudy();

        return w;
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}


