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
		//LocationData location = new LocationData("37.774929" ,"-122.419416", "location", "2024-05-22T14:30:00Z", this, id);
		TrafficLightData dataTrafficLight = new TrafficLightData(location, status, "30", "trafficlight", "2024-05-22T14:30:00Z", id);
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

}
