package com.example.young.gdg_yhkim.Model;

import io.realm.RealmObject;

/**
 * Created by apple on 2016-01-30.
 */
public class City extends RealmObject {
    public City(){

    }
    private String engname;
    private String korname;
    private String country;

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public String getKorname() {
        return korname;
    }

    public void setKorname(String korname) {
        this.korname = korname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
