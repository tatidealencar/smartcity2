package com.smarcity.SensingLayer.Model;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public class SpeedData extends Data {
    private static final long serialVersionUID = 1L; 
    private String sensorType;

    public SpeedData(String velocidade, String unidade, String sensorType, String timestamp, int sensingId) {
        super(velocidade, unidade, timestamp, sensingId);
        this.sensorType = sensorType;
    }

   public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String toString() {
        return sensorType + ": " + super.getLatitude() + " - " + super.getLongitude() + " - " + super.getTimestamp() 
            +  " - " + super.getSensingId();
    }
}