package com.example.gintas.weatherthing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

    private TextView firstHourTime;

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

        firstHourTime = (TextView) findViewById(R.id.timeText1);

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

        //Have the whole 5 day forecast available
        Weather weather5Day1[] = new Weather[36];

        @Override
        protected Weather doInBackground(String... params){
            Weather weather = new Weather();
            String data[] = ((new WeatherHTTPClient()).getWeatherData(params[0]));

            try {
                //get the weather data for current time and for the 5 day forecast
                weather = JSONParser.getWeather(data[0]);
                weather5Day1 = JSONParser.getWeatherMulti(data[1], 40);
                System.out.println("Getting here");

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
            temperature.setText("" + Math.round(((weather.temperature.getTemp() - 273.15) * 1.8) + 32) + " F");
            humidity.setText("" + weather.currentCondition.getHumidity() + "%");
            pressure.setText("" + weather.currentCondition.getPressure() + " hPa");
            windSpeed.setText("" + Math.round(weather.wind.getSpeed() * 2.23694) + " mph,");
            windDegree.setText("" + weather.wind.getDegrees() + " degrees");

            firstHourTime.setText("" + weather5Day1[0].currentCondition.getDateTime());


            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.firstHours);
            RelativeLayout threeHrHouseLayout = (RelativeLayout) findViewById(R.id.threeHrHouse);
            //NEED TO ADD ALL THE NEW ELEMENTS PROGRAMMATICALLY FOR THE WHOLE 5 DAY FORECAST

            for (int i = 0; i < weather5Day1.length; i++){
                //Create whatever needs to be created for each element of the 3hourly forecast

                //Definitely need the time -- SHOULD BE FORMATTED A LITTLE DIFFERENTLY

                RelativeLayout relPerDay = new RelativeLayout(getBaseContext());
                RelativeLayout.LayoutParams forecastRel = (RelativeLayout.LayoutParams) relPerDay.getLayoutParams();


                TextView textViewDateTime = new TextView(getBaseContext());
                TextView textViewTemp = new TextView(getBaseContext());
                TextView textViewHum = new TextView(getBaseContext());

                TextView timeText = (TextView) findViewById(R.id.timeText1);
                RelativeLayout.LayoutParams firstDay = (RelativeLayout.LayoutParams) findViewById(R.id.firstDay).getLayoutParams();

                //Done to allow the new textboxes to step out of the boundaries of the parent relative layout
                RelativeLayout.LayoutParams firstHrs = (RelativeLayout.LayoutParams) findViewById(R.id.firstHours).getLayoutParams();

                textViewDateTime.setText("" + weather5Day1[i].currentCondition.getDateTime());
                textViewTemp.setText("" + Math.round(((weather5Day1[i].temperature.getTemp() - 273.15) * 1.8) + 32) + " F");
                textViewHum.setText("" + weather5Day1[i].currentCondition.getHumidity() + "%");


                textViewDateTime.setPadding(0, 20, 0, 0);
                textViewTemp.setPadding(500, 20, 0, 0);
                textViewHum.setPadding(700, 20, 0, 0);
                //textViewDateTime.setId(i + 1);

                firstHrs.height += textViewDateTime.getHeight() + i*10;
                firstDay.height += textViewDateTime.getHeight() + i*10;      //This works fine, creates a gigantic layout box


                textViewDateTime.setY(timeText.getY() + /* timeText.getHeight() + */ i * 80);
                textViewTemp.setY(timeText.getY() + i * 80);
                textViewHum.setY(timeText.getY() + i * 80);

                threeHrHouseLayout.addView(textViewDateTime);
                threeHrHouseLayout.addView(textViewTemp);
                threeHrHouseLayout.addView(textViewHum);
            }

        }
    }
}
