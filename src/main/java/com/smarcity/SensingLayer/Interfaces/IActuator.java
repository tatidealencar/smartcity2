package com.smarcity.SensingLayer.Interfaces;

import com.smarcity.Enum.TrafficLightStatus;

public abstract class IActuator implements ISensing {

	public abstract void collectData(TrafficLightStatus status, int id);
}
