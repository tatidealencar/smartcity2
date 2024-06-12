package com.smarcity.SensingLayer.Interfaces;

import com.smarcity.SensingLayer.Model.Data;

public abstract class ISensor implements ISensing {

	public abstract void collectData(ISensing owner, int id, Data location);
	public abstract Data readData();

}
