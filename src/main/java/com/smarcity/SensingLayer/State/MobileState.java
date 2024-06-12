package com.smarcity.SensingLayer.State;

import java.io.Serializable;

import com.smarcity.SensingLayer.Interfaces.IMobile;

public interface MobileState extends Serializable {
	public void changeState(IMobile mobile);
}
