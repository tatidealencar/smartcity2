package com.smarcity.SensingLayer.Interfaces;

import com.smarcity.SensingLayer.Model.LocationData;
import com.smarcity.SensingLayer.State.MobileState;

public abstract class IMobile implements ISensing {
	
	private MobileState state;
	private int id;

	public abstract LocationData getCurrentLocation();

	public void setState(MobileState s) {
		this.state = s;
	}

	public MobileState getState() {
		return this.state;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId(){
		return id;
	}
}
