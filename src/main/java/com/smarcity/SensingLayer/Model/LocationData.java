package com.smarcity.SensingLayer.Model;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public class LocationData extends Data {
    private static final long serialVersionUID = 1L;

    public LocationData(String latitude, String longitude, ISensing owner) {
        super(latitude, longitude, owner, "location");
    }

    public LocationData(int id, String latitude, String longitude, String timestamp, ISensing owner) {
        super(id, latitude, longitude, timestamp, owner, "location");
    }

    public String toString() {
        return super.getSensorType() + ": " + super.getData1() + " - " + super.getData2();
    }
}