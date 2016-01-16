package com.example.young.gdg_yhkim;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
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
import java.util.ArrayList;

/**
 * Created by apple on 2016-01-15.
 */
public class OpenWeatherAPIClient {

    private final static String APP_ID = "cdc07c15d3d72285fb498d47c967c603";

    private static final String endPoint = "api.openweathermap.org";
    private static final String DATA = "data";
    private static final String VERSION = "2.5";
    private static final String APP_KEY = "appid";

    //daily forecast
    private static final String apiForecast = "forecast";   //Call 16 day/daily forecast data api
    private static final String apiDaily = "daily";
    private static final String CITY = "q";  //도시
    private static final String CNT = "cnt";  //개수
    private static final String UNITS = "units";  //단위
    private static final String FORMAT = "mode";  //XML or JSON

    //units
    private static final String METRIC = "metric";
    private static final String IMPERIAL = "imperial";

    public String getDailyForecastUrl(String strCity){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(endPoint)
                .appendPath(DATA)
                .appendPath(VERSION)
                .appendPath(apiForecast)
                .appendPath(apiDaily)
                .appendQueryParameter(APP_KEY, APP_ID)
                .appendQueryParameter(CITY, strCity)
                .appendQueryParameter(CNT, "7")
                .appendQueryParameter(UNITS, METRIC)
                .appendQueryParameter(FORMAT, "json");

        return builder.build().toString();
    }

    public ArrayList<Weather> getWeather(String strCity) {
        ArrayList<Weather> weatherArrayList;
        String urlString = getDailyForecastUrl(strCity);

        try {
            // call API by using HTTPURLConnection
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            // parse JSON
            weatherArrayList = parseJSON(json);

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

        return weatherArrayList;
    }

    private ArrayList<Weather> parseJSON(JSONObject json) throws JSONException {

        ArrayList<Weather> weatherArrayList = new ArrayList<>();

        int cnt = json.getInt("cnt");

        for(int i=0; i<cnt; i++) {

            Weather w = new Weather();

            w.setCityName(json.getJSONObject("city").getString("name"));
            w.setIon(json.getJSONObject("city").getJSONObject("coord").getInt("lat"));
            w.setLat(json.getJSONObject("city").getJSONObject("coord").getInt("lon"));

            JSONArray jsonArray = json.getJSONArray("list");

            w.setDatetime(jsonArray.getJSONObject(i).getLong("dt"));
            w.setTempMax(jsonArray.getJSONObject(i).getJSONObject("temp").getInt("max"));
            w.setTempMin(jsonArray.getJSONObject(i).getJSONObject("temp").getInt("min"));
            w.setTemprature(jsonArray.getJSONObject(i).getJSONObject("temp").getInt("day"));
            w.setHumidity(jsonArray.getJSONObject(i).getInt("humidity"));
            w.setPres(jsonArray.getJSONObject(i).getInt("humidity"));
            w.setWeather(jsonArray.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"));

            Log.e("aaa", " " + w);

            weatherArrayList.add(w);
        }

        return weatherArrayList;
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
