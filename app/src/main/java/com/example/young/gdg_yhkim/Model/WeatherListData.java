package com.example.young.gdg_yhkim.Model;

import java.util.ArrayList;

/**
 * Created by apple on 2016-01-29.
 */
public class WeatherListData {
    //coord

    Coord coord;

    ArrayList<WeatherDes> weather;
    String base;
    Main main;
    int visibility;

    // wind
    Wind wind;

    //clouds
    Cloud clouds;

    int dt;
    //sys
    Sys sys;
    int id;
    String name;
    int cod;


    public ArrayList<WeatherDes> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherDes> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }


    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }


    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Cloud getCloud() {
        return clouds;
    }

    public void setCloud(Cloud cloud) {
        this.clouds = cloud;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
