package com.smarcity.SensingLayer.Model;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public class LocationData extends Data {
    private static final long serialVersionUID = 1L;
    private String sensorType;
    private ISensing owner;
    private int trafficLightId;

    public LocationData(String latitude, String longitude, String sensorType, String timestamp, ISensing owner,
            int id) {
        super(latitude, longitude, timestamp, id);
        this.sensorType = sensorType;
        this.owner = owner;
    }

    public LocationData(String latitude, String longitude, String sensorType, String timestamp, ISensing owner, int id,
            int trafficLightId) {
        super(latitude, longitude, timestamp, id);
        this.sensorType = sensorType;
        this.owner = owner;
        this.trafficLightId = trafficLightId;
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
        return this.sensorType + ": " + super.getData1() + " - " + super.getData2() + " - " + super.getTimestamp()
                + " - " + owner.getClass();
    }

    public void setId(int id) {
        super.setSensorId(id);
    }

    public void setLatitude(String latitude) {
        super.setLData1(latitude);
    }

    public void setLongitude(String longitude) {
        super.setLData2(longitude);
    }

    public void setOwner(String owner) {
        setOwner(owner);
    }

    public void setTrafficLightId(int id) {
        this.trafficLightId = id;
    }

    public int getId() {
        return super.getSensorId();
    }
}