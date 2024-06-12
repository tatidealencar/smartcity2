package com.smarcity.SensingLayer.Model;

public class SpeedData extends Data {
    private static final long serialVersionUID = 1L; 
    private String sensorType;

    public SpeedData(String velocidade, String unidade, String sensorType, String timestamp, int id) {
        super(velocidade, unidade, timestamp, id);
        this.sensorType = sensorType;
    }

   public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String toString() {
        return sensorType + ": " + super.getData1() + " - " + super.getData2() + " - " + super.getTimestamp();
    }
}