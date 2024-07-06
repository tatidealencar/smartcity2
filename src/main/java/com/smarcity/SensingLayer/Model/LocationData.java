package com.smarcity.SensingLayer.Model;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public class LocationData extends Data {
    private static final long serialVersionUID = 1L;
    private String sensorType;
    private int trafficLightId;

    public LocationData(Data data) {
        // Create Mock LocationData
        super(data.getLatitude(), data.getLongitude(), data.getTimestamp(), data.getSensingId());
        this.sensorType = "LocationSensor";
    }

    public LocationData(String latitude, String longitude, String sensorType, String timestamp, int sensingId) {
        super(latitude, longitude, timestamp, sensingId);
        this.sensorType = sensorType;
    }

    public LocationData(String latitude, String longitude, String sensorType, String timestamp, int sensingId,
            int trafficLightId) {
        super(latitude, longitude, timestamp, sensingId);
        this.sensorType = sensorType;
        this.trafficLightId = trafficLightId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String toString() {
        return this.sensorType + ": " + super.getLatitude() + " - " + super.getLongitude() + " - " + super.getTimestamp()
                + " - " + super.getSensingId();
    }

    public void setLatitude(String latitude) {
        super.setLatitude(latitude);
    }

    public void setLongitude(String longitude) {
        super.setLongitude(longitude);
    }

    public void setTrafficLightId(int id) {
        this.trafficLightId = id;
    }

}