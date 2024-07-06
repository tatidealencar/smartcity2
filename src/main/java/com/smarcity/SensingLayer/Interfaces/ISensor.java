package com.smarcity.SensingLayer.Interfaces;

import com.smarcity.SensingLayer.Model.Data;

public abstract class ISensor implements ISensing {

	private int id;

	public abstract void collectData(Data location);
	public abstract Data readData();
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId(){
		return id;
	}

}
