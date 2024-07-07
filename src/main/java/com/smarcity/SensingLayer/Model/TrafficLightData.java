package com.smarcity.SensingLayer.Model;

import com.smarcity.Enum.TrafficLightStatus;
import com.smarcity.SensingLayer.Interfaces.ISensing;

public class TrafficLightData extends Data {
    private static final long serialVersionUID = 1L; 
    private LocationData location;
    

    public TrafficLightData(LocationData location, TrafficLightStatus status, String duration, String timestamp, ISensing owner) {
        super(status.toString(), duration, timestamp, owner, "trafficlight");
        this.location = location;
    }

    public LocationData getLocation() {
        return this.location;
    }

    public String toString() {
        return super.getSensorType() + ": " + location.getData1() + " - " + location.getData2() + " - " + super.getData1() + " - " + super.getData2() + " - " + super.getTimestamp();
    }
}
