package com.smarcity.SensingLayer.Model;

import com.smarcity.Enum.TrafficLightStatus;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public class TrafficLightData extends Data {
    private static final long serialVersionUID = 1L; 
    private LocationData location;
    private String timestamp;
    private TrafficLightStatus status;
    private String duration;

    public TrafficLightData(LocationData location, TrafficLightStatus status, String duration, String dataType, String timestamp, ISensing origin) {
        super(location.getData1(), location.getData2(), dataType, timestamp, origin);
        this.location = location;
        this.status = status;
        this.duration = duration;
        this.timestamp = timestamp;
        super.setDataType(dataType);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDataType() {
        return super.getDataType();
    }

    public void setDataType(String dataType) {
        super.setDataType(dataType);
    }

    public TrafficLightStatus getStatus(){
        return this.status;
    }

    public LocationData getLocation() {
        return this.location;
    }

    public String toString() {
        return super.getDataType() + ": " + location.getData1() + " - " + location.getData2() + " - " + duration + " - " + status + " - " + timestamp;
    }
}
