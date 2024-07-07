package com.smarcity.SensingLayer.Factory;

import java.sql.SQLException;

import com.smarcity.MiddlewareLayer.db.SensorDB;
import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.State.MobileState;

public class SmartphoneMobile extends IMobile {

	public SmartphoneMobile() {

	}

	public SmartphoneMobile(int id) {
		super.setId(id);
	}

	public SmartphoneMobile(MobileState state) throws SQLException {
		SensorDB db = new SensorDB();
		super.setState(state);
		super.setId(db.createSensor(this));
	}

	public IMobile getSensor(int id) {
		SensorDB db = new SensorDB();
		try {
			return (IMobile) db.readSensor(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getType() {
		return "SmartphoneMobile";
	}

	@Override
	public void sendData(NetworkManager networkManager) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	public String toString() {
		return "Smartphone" + super.getId();
	}
}
