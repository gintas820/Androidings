package com.example.gintas.weatherthing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.gintas.weatherthing.Location;
import com.example.gintas.weatherthing.Weather;

import java.sql.Time;

/**
 * Created by Gintas on 5/30/2015.
 */
public class JSONParser {

    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();

        //Create a JSON object from the data that's passed in
        JSONObject jsonObject = new JSONObject(data);

        //And parse all the info from the JSON object
        Location loc = new Location();

        JSONObject coordObj = getObject("coord", jsonObject);
        loc.setLatitude(getFloat("lat", coordObj));
        loc.setLongitude(getFloat("lon", coordObj));

        JSONObject sysObj = getObject("sys", jsonObject);
        loc.setCountry(getString("country", sysObj));
        loc.setSunrise(getInt("sunrise", sysObj));
        loc.setSunset(getInt("sunset", sysObj));
        loc.setCity(getString("name", jsonObject));
        //Assign the newly created location to the weather object
        weather.location = loc;

        //Get the info from the "weather" tag array, which only has one element when receiving current weather
        JSONArray jsonArray = jsonObject.getJSONArray("weather");

        //General weather info from "weather" tag in JSON
        JSONObject JSONWeather = jsonArray.getJSONObject(0);
        weather.currentCondition.setWeatherId(getInt("id", JSONWeather));
        weather.currentCondition.setCondition(getString("main", JSONWeather));
        weather.currentCondition.setDescription(getString("description", JSONWeather));
        weather.currentCondition.setIcon(getString("icon", JSONWeather));

        //Temperature, humidity and pressure info from "main" tag in JSON
        JSONObject mainObject = getObject("main", jsonObject);
        weather.currentCondition.setHumidity(getInt("humidity", mainObject));
        weather.currentCondition.setPressure(getInt("pressure", mainObject));
        weather.temperature.setMinTemp(getFloat("temp_min", mainObject));
        weather.temperature.setMaxTemp(getFloat("temp_max", mainObject));
        weather.temperature.setTemp(getFloat("temp", mainObject));

        //Wind info from "wind" tag in JSON
        JSONObject windObject = getObject("wind", jsonObject);
        weather.wind.setSpeed(getFloat("speed", windObject));
        weather.wind.setDegrees(getFloat("deg", windObject));

        //Cloud info from "clouds" tag in JSON
        JSONObject cloudsObject = getObject("clouds", jsonObject);
        weather.clouds.setPercent(getInt("all", cloudsObject));

        return weather;
    }

    //Used to parse the JSON data into an array, mainly when there are multiple days of information being pulled
    //Passes the JSON string as the first parameter, and the number of days covered as the second parameter
    public static Weather[] getWeatherMulti(String data, int days) throws JSONException{
        Weather[] weather = new Weather[days];

        //Create a JSON object
        JSONObject jsonObject = new JSONObject(data);

        Location location = new Location();

        JSONObject cityObject = getObject("city", jsonObject);
        location.setCity(getString("name", cityObject));
        location.setCountry(getString("country", cityObject));

        JSONObject coordinateObject = getObject("coord", cityObject);
        location.setLongitude(getFloat("lon", coordinateObject));
        location.setLatitude(getFloat("lat", coordinateObject));

        JSONArray weatherInfoArray = jsonObject.getJSONArray("list");

        //Go through all the elements of the array and parse their info
        for(int i = 0; i < weatherInfoArray.length() && i < weather.length; i++){
            weather[i].location = location;

            JSONObject jsonWeather = weatherInfoArray.getJSONObject(i);

            JSONObject mainObj = getObject("main", jsonWeather);
            JSONObject weatherObj = getObject("weather", jsonWeather);
            JSONObject cloudsObj = getObject("clouds", jsonWeather);
            JSONObject windObj = getObject("wind", jsonWeather);
            JSONObject rainObj = getObject("rain", jsonWeather);

            weather[i].temperature.setTemp(getFloat("temp", mainObj));
            weather[i].temperature.setMaxTemp(getFloat("temp_max", mainObj));
            weather[i].temperature.setMinTemp(getFloat("temp_min", mainObj));
            weather[i].currentCondition.setPressure(getFloat("pressure", mainObj));
            weather[i].currentCondition.setHumidity(getInt("humidity", mainObj));

            weather[i].currentCondition.setWeatherId(getInt("id", weatherObj));
            weather[i].currentCondition.setCondition(getString("main", weatherObj));
            weather[i].currentCondition.setDescription(getString("description", weatherObj));
            weather[i].currentCondition.setIcon(getString("icon", weatherObj));

            weather[i].currentCondition.setDateTime(getString("dt_txt", jsonWeather));

            weather[i].clouds.setPercent(getInt("all", cloudsObj));

            weather[i].wind.setDegrees(getFloat("deg", windObj));
            weather[i].wind.setSpeed(getFloat("speed", windObj));

            weather[i].rain.setAmount(getFloat("3h", rainObj));

        }


        return weather;
    }

    //Pass in the tag name as the first argument and the JSON object as the second argument
    //Returns the appropriate tag as a JSON object
    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException{
        JSONObject subObject = jObj.getJSONObject(tagName);
        return subObject;
    }

    //Helper methods to get String, float, and int values
    private static String getString(String tagName, JSONObject jObj)throws JSONException{
        return jObj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jObj)throws JSONException{
        return (float) jObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jObj)throws JSONException{
        return jObj.getInt(tagName);
    }


}
