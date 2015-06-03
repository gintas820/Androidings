package com.example.gintas.weatherthing;

import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Created by Gintas on 5/30/2015.
 */
public class Weather {

    public Location location;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Rain rain = new Rain();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

    public byte[] iconData;

    public class CurrentCondition{

        private int weatherId;
        private String condition;
        private String description;
        private String icon;

        private float pressure;
        private float humidity;





        //Getters and setters
        public int getWeatherId() {
            return weatherId;
        }
        public void setWeatherId(int weatherId) {
            this.weatherId = weatherId;
        }

        public String getCondition() {
            return condition;
        }
        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }
        public void setIcon(String icon) {
            this.icon = icon;
        }

        public float getPressure() {
            return pressure;
        }
        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public float getHumidity() {
            return humidity;
        }
        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }


    }

    public class Temperature{

        private float temp;
        private float minTemp;
        private float maxTemp;


        //Getters and setters
        public float getTemp() {
            return temp;
        }
        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getMinTemp() {
            return minTemp;
        }
        public void setMinTemp(float minTemp) {
            this.minTemp = minTemp;
        }

        public float getMaxTemp() {
            return maxTemp;
        }
        public void setMaxTemp(float maxTemp) {
            this.maxTemp = maxTemp;
        }
    }

    public class Wind {

        private float speed;
        private float degrees;

        //Getters and setters
        public float getSpeed() {
            return speed;
        }
        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public float getDegrees() {
            return degrees;
        }
        public void setDegrees(float degrees) {
            this.degrees = degrees;
        }
    }

    public class Rain{

        private String time;
        private float amount;

        //Getters and setters
        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }

        public float getAmount() {
            return amount;
        }
        public void setAmount(float amount) {
            this.amount = amount;
        }
    }

    public class Snow{

        private  String time;
        private float amount;

        //Getters and setters
        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }

        public float getAmount() {
            return amount;
        }
        public void setAmount(float amount) {
            this.amount = amount;
        }
    }

    public class Clouds{

        private int percent;

        //Getters and setters
        public int getPercent() {
            return percent;
        }
        public void setPercent(int percent) {
            this.percent = percent;
        }
    }
}
