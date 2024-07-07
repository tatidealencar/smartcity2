package com.smarcity.SensingLayer.Factory;

import com.smarcity.Enum.TrafficLightStatus;
import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.IActuator;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.LocationData;
import com.smarcity.SensingLayer.Model.TrafficLightData;
public class TrafficLightActuator extends IActuator {

	private TrafficLightData trafficLightData;
	private int id;

	public void collectData(TrafficLightStatus status, int id, LocationData location) {
		TrafficLightData dataTrafficLight = new TrafficLightData(location, status, "30", "2024-05-22T14:30:00Z", location.getOwner());
		this.trafficLightData = dataTrafficLight;
		this.id = id;
	}

	@Override
	public String getType() {
		return "TrafficLightActuator";
	}

	@Override
	public Data readData() {
		return this.trafficLightData;
	}

	@Override
	public void sendData(NetworkManager networkManager) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	public String toString() {
		return trafficLightData.getSensorType() + ": " + trafficLightData.getData1() + " - " + trafficLightData.getData2() + " - " +  trafficLightData.getTimestamp();
	}

	@Override
	public int getId() {
		return this.id;
	}

}
