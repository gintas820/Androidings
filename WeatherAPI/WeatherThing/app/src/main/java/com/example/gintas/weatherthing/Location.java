package com.example.gintas.weatherthing;

import java.io.Serializable;
import java.security.PrivateKey;

/**
 * Created by Gintas on 5/30/2015.
 */
public class Location implements Serializable{

    private float longitude;
    private float latitude;
    private long sunset;
    private long sunrise;
    private String country;
    private String city;

    //Getters and setters
    public float getLongitude(){
        return longitude;
    }
    public void setLongitude(float l){
        this.longitude = l;
    }

    public float getLatitude(){
        return latitude;
    }
    public void setLatitude(float l){
        this.latitude = l;
    }

    public long getSunset(){
        return sunset;
    }
    public void setSunset(long s){
        this.sunset = s;
    }

    public long getSunrise(){
        return sunrise;
    }
    public void setSunrise(long s){
        this.sunrise = s;
    }

    public String getCountry(){
        return country;
    }
    public void setCountry(String c) {
        this.country = c;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String c) {
        this.city = c;
    }
}
