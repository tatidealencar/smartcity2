package com.smarcity.SensingLayer.Factory;

import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.Model.LocationData;
import com.smarcity.SensingLayer.State.DisconnectedState;

public class SmartphoneMobile extends IMobile {

	private LocationData location;

	public SmartphoneMobile(int id) {
		super.setState(DisconnectedState.getInstance());
		super.setId(id);
		LocationData location = new LocationData("37.774929" ,"-122.419416", "location", "2024-05-22T14:30:00Z", this);
		this.location = location;
	}

	public void updateLocation() {
		LocationData location = new LocationData("37.774929" ,"-122.419416", "location", "2024-05-22T14:30:00Z", this);
		this.location = location;
	}

	@Override
	public String getType() {
		return "SmartphoneMobile";
	}

	@Override
	public LocationData getCurrentLocation() {
		return this.location;
	}

	@Override
	public void sendData(NetworkManager networkManager) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	public String toString() {
		return "Smartphone | " + this.location.getDataType() + ": " + this.location.getData1() + " - " + this.location.getData2() + " - " +  this.location.getTimestamp();
	}
}
