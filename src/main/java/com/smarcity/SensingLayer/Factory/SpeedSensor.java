package com.smarcity.SensingLayer.Factory;

import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Interfaces.ISensor;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.SpeedData;

class SpeedSensor extends ISensor {

	private SpeedData speed;

	public void collectData(Data dataSpeed) {
		//SpeedData dataSpeed = new SpeedData("300", "Km/h", "speed", "2024-05-22T14:30:00Z", id);
		this.speed = (SpeedData) dataSpeed;
	}

	@Override
	public String getType() {
		return "SpeedSensor";
	}

	@Override
	public SpeedData readData() {
		return this.speed;
	}

	@Override
	public void sendData(NetworkManager networkInterface5G) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	public String toString() {
		return speed.getDataType() + ": " + speed.getData1() + " - " + speed.getData2() + " - " +  speed.getTimestamp();
	}
}
