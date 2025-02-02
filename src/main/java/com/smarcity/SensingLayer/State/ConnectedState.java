package com.smarcity.SensingLayer.State;

import com.smarcity.SensingLayer.Interfaces.IMobile;

public class ConnectedState implements MobileState {

	private static ConnectedState instance;

	public static ConnectedState getInstance() {
		if (instance == null) {
			instance = new ConnectedState();
		}
		return instance;
	}

	private ConnectedState() {}

	@Override
	public void changeState(IMobile mobile) {
		System.out.println("The state changed to connected");
	}

	public String toString() {
		return "ConnectedState";
	}

}
