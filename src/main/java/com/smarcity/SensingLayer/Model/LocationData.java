package com.smarcity.SensingLayer.Model;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public class LocationData extends Data {
    private static final long serialVersionUID = 1L; 
    private String sensorType;
    private ISensing owner;

    public LocationData(String latitude, String longitude, String sensorType, String timestamp, ISensing owner, int id) {
        super(latitude, longitude, timestamp, id);
        this.sensorType = sensorType;
        this.owner = owner;
    }
    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public ISensing getOwner() {
        return this.owner;
    }

    public String toString() {
        return this.sensorType + ": " + super.getData1() + " - " + super.getData2() + " - " + super.getTimestamp() + " - " + owner.getClass();
    }
}