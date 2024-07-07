package com.smarcity.SensingLayer.Model;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public class LocationData extends Data {
    private static final long serialVersionUID = 1L;
    private int trafficLightId;

    public LocationData(String latitude, String longitude, String timestamp, ISensing owner) {
        super(latitude, longitude, timestamp, owner, "location");
    }

    public LocationData(String latitude, String longitude, String timestamp, ISensing owner, int trafficLightId) {
        super(latitude, longitude, timestamp, owner, "location");
        this.trafficLightId = trafficLightId;
    }


    public void setTrafficLightId(int id) {
        this.trafficLightId = id;
    }

    public int getTrafficLightId() {
        return this.trafficLightId;
    }

    public String toString() {
        return super.getSensorType() + ": " + super.getData1() + " - " + super.getData2() + " - " + super.getTimestamp();
    }
}