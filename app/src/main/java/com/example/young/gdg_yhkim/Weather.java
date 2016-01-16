package com.example.young.gdg_yhkim;

/**
 * Created by apple on 2016-01-15.
 */
public class Weather {
    int lat;            //위도
    int ion;            //경도
    int temprature;    //온도
    int cloudy;
    String strCity;     //도시
    int pres;           //압력
    int humidity;      //습도
    int tempMin;      //최저온도
    int tempMax;      //최고온도

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setIon(int ion) {
        this.ion = ion;
    }

    public void setTemprature(int t) {
        this.temprature = t;
    }

    public void setCloudy(int cloudy) {
        this.cloudy = cloudy;
    }

    public void setCity(String city) {
        this.strCity = city;
    }

    public void setPres(int pres) {
        this.pres = pres;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public int getLat() {
        return lat;
    }

    public int getIon() {
        return ion;
    }

    public int getTemprature() {
        return temprature;
    }

    public int getCloudy() {
        return cloudy;
    }

    public String getCity() {
        return strCity;
    }

    public int getPres() {
        return pres;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getTempMin() {
        return tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }
}
