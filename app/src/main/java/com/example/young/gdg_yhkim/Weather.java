package com.example.young.gdg_yhkim;

/**
 * Created by apple on 2016-01-15.
 */
public class Weather {
    int lat;            //위도
    int ion;            //경도
    int temprature;    //온도

    String cityName;     //도시
    int pres;           //압력
    int humidity;      //습도
    int tempMin;      //최저온도

    @Override
    public String toString() {
        return "Weather{" +
                "lat=" + lat +
                ", ion=" + ion +
                ", temprature=" + temprature +
                ", cityName='" + cityName + '\'' +
                ", pres=" + pres +
                ", humidity=" + humidity +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", datetime=" + datetime +
                ", weather='" + weather + '\'' +
                '}';
    }

    int tempMax;      //최고온도


    long datetime;  //날짜
    String weather;  //날씨

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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
