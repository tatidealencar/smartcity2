package com.smarcity.SensingLayer.State;

import java.io.Serializable;

import com.smarcity.SensingLayer.Interfaces.IMobile;

public interface MobileState extends Serializable {
	public void changeState(IMobile mobile);

	public static MobileState getState(String state) {
        switch (state) {
            case "DisconnectedState":
                return DisconnectedState.getInstance();
            case "ConnectedState":
                return ConnectedState.getInstance();
            default:
                throw new IllegalArgumentException("Unknown state: " + state);
        }
    }
}
