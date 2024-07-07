package com.smarcity.SensingLayer.Factory;

import java.sql.SQLException;

import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.State.MobileState;

public class FactoryMobile {

	public Vehicle createVehicle(MobileState state) throws SQLException {
		return new Vehicle(state);
	}

	public SmartphoneMobile createSmartphoneMobile(MobileState state) throws SQLException {
		return new SmartphoneMobile(state);
	}

	public IMobile createVehicle(int id) {
		return new Vehicle(id);
	}

	public IMobile createSmartphoneMobile(int id) {
		return new SmartphoneMobile(id);
	}

}
