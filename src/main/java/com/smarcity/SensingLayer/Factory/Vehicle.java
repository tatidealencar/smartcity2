package com.smarcity.SensingLayer.Factory;

import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.Model.LocationData;
import com.smarcity.SensingLayer.State.ConnectedState;
import com.smarcity.SensingLayer.State.DisconnectedState;

public class Vehicle extends IMobile {

	private LocationData location;

	public Vehicle(int id) {
		super.setState(DisconnectedState.getInstance());
		super.setId(id);
		LocationData location = new LocationData("37.774929" ,"-122.419416", "location", "2024-05-22T14:30:00Z", this);
		this.location = location;
	}

	public void updateLocation(LocationData location) {

		LocationData viaLocation = new LocationData("37.774929", "-122.419416", "location", "2024-05-22T14:30:00Z", this);

		this.location = location;
		if (location == viaLocation) { // mudar para se location do veículo está próxima da location da via
			setState(ConnectedState.getInstance());
		} else {
			setState(DisconnectedState.getInstance());
		}
	}

	@Override
	public String getType() {
		return "Vehicle";
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
		return "Vehicle | " + location.getDataType() + ": " + location.getData1() + " - " + location.getData2()
				+ " - " + location.getTimestamp();
	}

}
