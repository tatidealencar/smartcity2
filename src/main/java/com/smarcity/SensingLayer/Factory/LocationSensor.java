package com.smarcity.SensingLayer.Factory;

import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.ISensor;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.LocationData;

class LocationSensor extends ISensor {

	private LocationData location;
	private int id;

	public void collectData(int id, Data dataLocation) {
		this.location = (LocationData) dataLocation;
		this.id = id;
	}

	@Override
	public String getType() {
		return "LocationSensor";
	}

	@Override
	public LocationData readData() {
		return this.location;
	}

	@Override
	public void sendData(NetworkManager networkInterface5G) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	@Override
	public int getId() {
		return this.id;
	}

	public String toString() {
		return this.location.getSensorType() + ": " + location.getData1() + " - " + location.getData2() + " - "
				+ location.getTimestamp();
	}

}
