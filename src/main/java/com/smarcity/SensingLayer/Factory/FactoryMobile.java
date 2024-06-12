package com.smarcity.SensingLayer.Factory;

public class FactoryMobile {

	public Vehicle createVehicle(int id) {
		return new Vehicle(id);
	}

	public SmartphoneMobile createSmartphoneMobile(int id) {
		return new SmartphoneMobile(id);
	}

}
