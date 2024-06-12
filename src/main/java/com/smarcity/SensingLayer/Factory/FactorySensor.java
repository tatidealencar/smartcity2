package com.smarcity.SensingLayer.Factory;

public class FactorySensor {

	public SpeedSensor createSpeedSensor() {
		return new SpeedSensor();
	}

	public LocationSensor createLocationSensor() {
		return new LocationSensor();
	}

}
