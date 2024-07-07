package com.smarcity.SensingLayer.Factory;

import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.State.DisconnectedState;

public class SmartphoneMobile extends IMobile {

	public SmartphoneMobile(int id) {
		super.setState(DisconnectedState.getInstance());
		super.setId(id);
	}

	@Override
	public String getType() {
		return "SmartphoneMobile";
	}

	@Override
	public void sendData(NetworkManager networkManager) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	public String toString() {
		return "Smartphone" + super.getId();
	}
}
