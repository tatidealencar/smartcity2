package com.smarcity.SensingLayer.Interfaces;

import com.smarcity.SensingLayer.State.MobileState;

public abstract class IMobile implements ISensing {
	
	private MobileState state;

	private int id;

	public void setState(MobileState s) {
		this.state = s;
	}

	public MobileState getState() {
		return this.state;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
