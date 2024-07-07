package com.smarcity.SensingLayer.State;

import com.smarcity.SensingLayer.Interfaces.IMobile;

public class DisconnectedState implements MobileState {

	private static DisconnectedState instance;

	public static DisconnectedState getInstance() {
		if (instance == null) {
			instance = new DisconnectedState();
		}
		return instance;
	}

	private DisconnectedState() {
		// Construtor privado para evitar instanciamento
	}

	@Override
	public void changeState(IMobile mobile) {
		System.out.println("The state changed to disconnected");
	}

	public String toString() {
		return "DisconnectedState";
	}
}
