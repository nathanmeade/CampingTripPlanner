package com.example.campingtripplanner;

public class Main {
    private double temp;
    private String pressure;
    private String humidity;
    private String temp_min;
    private String temp_max;
    private String sea_level;

    public String getHumidity() {
        return humidity;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public String getSea_level() {
        return sea_level;
    }

    public String getGrnd_level() {
        return grnd_level;
    }

    private String grnd_level;

    public double getTemp(){return temp;}
    public String getPressure(){return pressure;}

}

