package com.smarcity.SensingLayer.Interfaces;

import com.smarcity.Enum.TrafficLightStatus;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.LocationData;

public abstract class IActuator implements ISensing {

	public abstract void collectData(TrafficLightStatus status, int id, LocationData location);
	public abstract Data readData();
}
