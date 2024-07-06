package com.smarcity.SensingLayer.Factory;

import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.ISensor;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.LocationData;
class LocationSensor extends ISensor {

	private LocationData location;
	private int id;

	public void collectData(Data dataLocation) {
		//LocationData dataLocation = new LocationData("37.774929" ,"-122.419416", "location", currentTimestamp.toString(), owner, id);
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

	public String toString() {
		return this.location.getDataType() + ": " + location.getData1() + " - " + location.getData2() + " - " +  location.getTimestamp();
	}

	@Override
	public void sendData(NetworkManager networkInterface5G) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	public int getId(){
		return id;
	}

}
