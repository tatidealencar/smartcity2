package com.smarcity.SensingLayer.Model;

import com.smarcity.Enum.TrafficLightStatus;

public class TrafficLightData extends Data {
    private static final long serialVersionUID = 1L; 
    private LocationData location;
    private String timestamp;
    private TrafficLightStatus status;
    private String duration;
    private String sensorType;

    public TrafficLightData(LocationData location, TrafficLightStatus status, String duration, String sensorType, String timestamp, int sensingId) {
        super(location.getLatitude(), location.getLongitude(), timestamp, sensingId);
        this.location = location;
        this.status = status;
        this.duration = duration;
        this.timestamp = timestamp;
        this.sensorType = sensorType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public TrafficLightStatus getStatus(){
        return this.status;
    }

    public LocationData getLocation() {
        return this.location;
    }

    public String toString() {
        return sensorType + ": " + location.getLatitude() + " - " + location.getLongitude() + " - " + duration + " - " + status + " - " + timestamp;
    }
}
