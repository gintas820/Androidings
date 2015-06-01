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
    private static String IMG_URL = "http://openweathermap.org/img/w/";

    public String getWeatherData(String location){
        HttpURLConnection connection = null;
        InputStream input = null;

        try{
            //Set up the connection
            connection = (HttpURLConnection) (new URL(BASE_URL + location)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //Read the response
            StringBuffer buffer = new StringBuffer();
            input = connection.getInputStream();
            BufferedReader buffRead = new BufferedReader(new InputStreamReader(input));
            String line = null;

            while ((line = buffRead.readLine()) != null) {
                buffer.append(line + "\r\n");
            }

                input.close();
                connection.disconnect();
                return buffer.toString();
        } catch (Throwable t){
            t.printStackTrace();
        } finally {
            try {
                input.close();
            }
            catch (Throwable t){}
            try {
                connection.disconnect();
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
