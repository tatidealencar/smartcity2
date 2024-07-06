package com.smarcity.SensingLayer.Interfaces;

import com.smarcity.Enum.TrafficLightStatus;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.LocationData;

public abstract class IActuator implements ISensing {
	private int sensorId;

	public abstract void collectData(TrafficLightStatus status, int id, LocationData location);
	public abstract Data readData();

	public int getSensorId() {
		return this.sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
}
