package com.example.gintas.weatherthing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;


import com.example.gintas.weatherthing.Weather;
import com.example.gintas.weatherthing.Location;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends ActionBarActivity {

    private TextView cityText;
    private TextView conditionDesc;
    private TextView temperature;
    private TextView humidity;
    private TextView pressure;
    private TextView windSpeed;
    private TextView windDegree;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String city = "Philadelphia,USA";

        cityText = (TextView) findViewById(R.id.cityText);
        conditionDesc = (TextView) findViewById(R.id.conditionDesc);
        temperature = (TextView) findViewById(R.id.temperature);
        humidity = (TextView) findViewById(R.id.humidity);
        pressure = (TextView) findViewById(R.id.pressure);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        windDegree = (TextView) findViewById(R.id.windDeg);
        imgView = (ImageView) findViewById(R.id.conditionIcon);

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather>{

        @Override
        protected Weather doInBackground(String... params){
            Weather weather = new Weather();
            String data = ((new WeatherHTTPClient()).getWeatherData(params[0]));

            try {
                weather = JSONParser.getWeather(data);
                //Get the icon
                weather.iconData = ((new WeatherHTTPClient()).getImage(weather.currentCondition.getIcon()));
            }catch (JSONException e){
                e.printStackTrace();
            }
            return weather;
        }

        @Override
        protected  void onPostExecute(Weather weather){
            super.onPostExecute(weather);

            if(weather.iconData != null && weather.iconData.length > 0){
                Bitmap image = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(image);
            }

            cityText.setText(weather.location.getCity() + ", " + weather.location.getCountry());
            conditionDesc.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescription() + ")");
            temperature.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + "C");
            humidity.setText("" + weather.currentCondition.getHumidity() + "%");
            pressure.setText("" + weather.currentCondition.getPressure() + " hPa");
            windSpeed.setText("" + weather.wind.getSpeed() + " mps");
            windDegree.setText("" + weather.wind.getDegrees() + " degrees");
        }
    }
}
