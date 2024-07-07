package com.smarcity.SensingLayer.Model;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public class SpeedData extends Data {
    private static final long serialVersionUID = 1L; 

    public SpeedData(String velocidade, String timestamp, ISensing owner) {
        super(velocidade, "Km/h", timestamp, owner, "speed");
    }

    public String toString() {
        return super.getSensorType() + ": " + super.getData1() + " - " + super.getData2() + " - " + super.getTimestamp();
    }
}