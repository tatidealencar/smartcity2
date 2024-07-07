package com.smarcity.SensingLayer.Factory;

import java.sql.SQLException;

import com.smarcity.MiddlewareLayer.db.SensorDB;
import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.State.DisconnectedState;
import com.smarcity.SensingLayer.State.MobileState;

public class Vehicle extends IMobile {

	public Vehicle() {
	}

	public Vehicle(int id) {
		super.setState(DisconnectedState.getInstance());
		super.setId(id);
	}

	public Vehicle(MobileState state) throws SQLException {
		super.setState(state);
		SensorDB db = new SensorDB();
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
		return "Vehicle";
	}

	@Override
	public void sendData(NetworkManager networkManager) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	public String toString() {
		return "Vehicle" + super.getId();
	}
}
