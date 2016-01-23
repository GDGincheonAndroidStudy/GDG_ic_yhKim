package com.example.young.gdg_yhkim.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by apple on 2016-01-22.
 */
public class WeatherInfo {
    @Expose
    int lat;            //위도
    @Expose
    int ion;            //경도
    @Expose
    @SerializedName("temp")
    int temprature;    //온도
    @Expose
    @SerializedName("name")
    String strCity;     //도시
    @Expose
    @SerializedName("pressure")
    int pres;           //압력
    @Expose
    int humidity;      //습도
    @Expose
    @SerializedName("temp_min")
    int tempMin;      //최저온도
    @Expose
    @SerializedName("temp_max")
    int tempMax;      //최고온도

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getIon() {
        return ion;
    }

    public void setIon(int ion) {
        this.ion = ion;
    }

    public int getTemprature() {
        return temprature;
    }

    public void setTemprature(int temprature) {
        this.temprature = temprature;
    }

    public String getStrCity() {
        return strCity;
    }

    public void setStrCity(String strCity) {
        this.strCity = strCity;
    }

    public int getPres() {
        return pres;
    }

    public void setPres(int pres) {
        this.pres = pres;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }
}
