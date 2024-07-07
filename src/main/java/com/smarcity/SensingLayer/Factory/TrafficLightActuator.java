package com.smarcity.SensingLayer.Factory;

import java.sql.SQLException;

import com.smarcity.Enum.TrafficLightStatus;
import com.smarcity.MiddlewareLayer.db.SensorDB;
import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Interfaces.IActuator;

public class TrafficLightActuator extends IActuator {

	TrafficLightStatus status;
	private int id;
	
	public TrafficLightActuator() throws SQLException {
		this.status = TrafficLightStatus.RED;
		SensorDB db = new SensorDB();
		this.id = db.createSensor(this);
	}

	public void collectData(TrafficLightStatus status, int id) {
		this.status = status;
		this.id = id;
	}

	public IActuator getSensor(int id) {
		SensorDB db = new SensorDB();
		try {
			return (IActuator) db.readSensor(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static TrafficLightStatus getState(String status) {
		switch (status) {
			case "RED":
				return TrafficLightStatus.RED;
			case "YELLOW":
				return TrafficLightStatus.YELLOW;
			case "GREEN":
				return TrafficLightStatus.GREEN;
			default:
				throw new IllegalArgumentException("Unknown state: " + status);
		}
	}

	@Override
	public String getType() {
		return "TrafficLightActuator";
	}

	@Override
	public void sendData(NetworkManager networkManager) {
		throw new UnsupportedOperationException("Unimplemented method 'sendData'");
	}

	@Override
	public int getId() {
		return this.id;
	}

	public TrafficLightStatus getStatus() {
		return this.status;
	}

}
