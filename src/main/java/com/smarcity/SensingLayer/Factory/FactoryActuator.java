package com.smarcity.SensingLayer.Factory;

import java.sql.SQLException;

public class FactoryActuator {

	public TrafficLightActuator createTrafficLightActuator() throws SQLException {
		return new TrafficLightActuator();
	}

}
