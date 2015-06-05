package com.example.gintas.weatherthing;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Gintas on 5/30/2015.
 */
public class WeatherHTTPClient {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String FORECAST_5_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private static String FORECAST_10_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Philadelphia&cnt=10";
    private static String FORECAST_16_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Philadelphia&cnt=16";
    private static String IMG_URL = "http://openweathermap.org/img/w/";


    //NEED TO CHANGE THIS TO AN ARRAY OF STRINGS, SO IT COULD RETURN MULTIPLE VALUES
    public String[] getWeatherData(String location){
        String[] response = new String[2];
        HttpURLConnection connection = null;
        HttpURLConnection connection5Day = null;
        InputStream input = null;
        InputStream input5Day = null;

        try{
            //Set up the connection for current conditions
            connection = (HttpURLConnection) (new URL(BASE_URL + location)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //Set up the connection for five day forecast for every 3 hours
            connection5Day = (HttpURLConnection) (new URL(FORECAST_5_URL + location)).openConnection();
            connection5Day.setRequestMethod("GET");
            connection5Day.setDoInput(true);
            connection5Day.setDoOutput(true);
            connection5Day.connect();

            //Read the response for the first
            StringBuffer buffer = new StringBuffer();
            input = connection.getInputStream();
            BufferedReader buffRead = new BufferedReader(new InputStreamReader(input));
            String line = null;

            //And read for the second
            StringBuffer buffer1 = new StringBuffer();
            input5Day = connection5Day.getInputStream();
            BufferedReader buffRead1 = new BufferedReader(new InputStreamReader(input5Day));
            String line5Day = null;


            while ((line = buffRead.readLine()) != null) {
                buffer.append(line + "\r\n");
            }
                input.close();
                connection.disconnect();

            while ((line5Day = buffRead1.readLine()) != null){
                buffer1.append(line5Day + "\r\n");
            }
                input5Day.close();
                connection5Day.disconnect();

                response[0] = buffer.toString();
                response[1] = buffer1.toString();


                return response;
        } catch (Throwable t){
            t.printStackTrace();
        } finally {
            try {
                input.close();
                input5Day.close();
            }
            catch (Throwable t){}
            try {
                connection.disconnect();
                connection5Day.disconnect();
            }
            catch (Throwable t){}
        }
        return null;
    }

    public byte[] getImage(String code){

        HttpURLConnection connection = null;
        InputStream input = null;

        try{
            //Set up the connection
            connection = (HttpURLConnection) (new URL(IMG_URL + code + ".png")).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //Read out the response
            input = connection.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while(input.read(buffer) != -1)
                baos.write(buffer);

            return  baos.toByteArray();
        }catch (Throwable t){
            t.printStackTrace();
        }finally {
            try {
                input.close();
            } catch (Throwable t){}
            try {
                connection.disconnect();
            }catch (Throwable t){}
        }
        return null;
    }
}