package com.smarcity.SensingLayer.Factory;

import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.ISensor;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.SpeedData;

class SpeedSensor extends ISensor {

	private SpeedData speed;
	private int id;

	public void collectData(int id, Data dataSpeed) {
		this.speed = (SpeedData) dataSpeed;
		this.id = id;
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
		return speed.getSensorType() + ": " + speed.getData1() + " - " + speed.getData2() + " - " +  speed.getTimestamp();
	}

	@Override
	public int getId() {
		return this.id;
	}
}
