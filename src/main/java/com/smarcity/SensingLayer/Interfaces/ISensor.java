package com.smarcity.SensingLayer.Interfaces;

import com.smarcity.SensingLayer.Model.Data;

public abstract class ISensor implements ISensing {
	private int sensorId;

	public abstract void collectData(ISensing owner, int id, Data location);
	public abstract Data readData();
	public int getSensorId() {
		return this.sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

}
