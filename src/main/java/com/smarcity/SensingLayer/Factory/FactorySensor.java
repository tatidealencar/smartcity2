package com.smarcity.SensingLayer.Factory;

import java.sql.SQLException;

public class FactorySensor {

	public SpeedSensor createSpeedSensor() throws SQLException {
		return new SpeedSensor();
	}

	public LocationSensor createLocationSensor() throws SQLException {
		return new LocationSensor();
	}

	public SpeedSensor createSpeedSensor(int id) throws SQLException {
		return new SpeedSensor();
	}

	public LocationSensor createLocationSensor(int id) throws SQLException {
		return new LocationSensor();
	}


}
