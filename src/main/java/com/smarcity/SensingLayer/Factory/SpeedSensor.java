package com.smarcity.SensingLayer.Factory;

import java.sql.SQLException;

import com.smarcity.MiddlewareLayer.db.SensorDB;
import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.ISensor;

class SpeedSensor extends ISensor {

	private int id;

	public ISensor getSensor(int id) {
		SensorDB db = new SensorDB();
		try {
			return (ISensor) db.readSensor(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SpeedSensor() throws SQLException {
		SensorDB db = new SensorDB();
		this.id = db.createSensor(this);
	}

	public SpeedSensor(int id) {
		this.id = id;
	}
	
	@Override
	public String getType() {
		return "SpeedSensor";
	}

	@Override
	public void sendData(NetworkManager networkInterface5G) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	@Override
	public int getId() {
		return this.id;
	}
}
