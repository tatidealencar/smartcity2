package com.smarcity.SensingLayer.Factory;

import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.State.DisconnectedState;

public class Vehicle extends IMobile {

	public Vehicle(int id) {
		super.setState(DisconnectedState.getInstance());
		super.setId(id);
	}

	@Override
	public String getType() {
		return "Vehicle";
	}

	@Override
	public void sendData(NetworkManager networkManager) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	public String toString() {
		return "Vehicle" + super.getId();
	}
}
