package com.smarcity.ApplicationLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.smarcity.BusinessLayer.AnalysisResult;
import com.smarcity.BusinessLayer.AnalyticsEngine;
import com.smarcity.Enum.TrafficLightStatus;
import com.smarcity.MiddlewareLayer.db.DataDB;
import com.smarcity.MiddlewareLayer.db.SensorDB;
import com.smarcity.SensingLayer.Factory.SmartphoneMobile;
import com.smarcity.SensingLayer.Factory.Vehicle;
import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Interfaces.ISensor;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.LocationData;
import com.smarcity.SensingLayer.Model.SpeedData;
import com.smarcity.SensingLayer.Model.TrafficLightData;
import com.smarcity.SensingLayer.State.DisconnectedState;

public class TrafficMonitor implements Subject {

	private List<Observer> observers = new ArrayList<>();

	private AnalysisResult currentResult;
	private AnalysisResult previousResult;

	private static TrafficMonitor instance;

	private TrafficMonitor() {
		this.currentResult = new AnalysisResult(false, "No collision detected");
		this.previousResult = new AnalysisResult(false, "No collision detected");
	}

	public static TrafficMonitor getInstance() {
		if (instance == null) {
			instance = new TrafficMonitor();
		}
		return instance;
	}

	public void analyzeTrafficData(List<Data> dataList) {

		Data locationVehicle = null;
		Data speedVehicle = null;
		List<Data> locationSmartphones = new ArrayList<>();
		Data locationTrafficLight = null;
		TrafficLightStatus trafficLightStatus = null;

		for (Data data : dataList) {
			if (data instanceof LocationData) {
				ISensing owner = ((LocationData) data).getOwner();
				if (owner instanceof SmartphoneMobile) {
					locationSmartphones.add(data);
				} else if (owner instanceof Vehicle) {
					locationVehicle = new LocationData(data.getId(), data.getData1(), data.getData2(),
							data.getTimestamp(), owner);
				}
			} else if (data instanceof SpeedData) {
				speedVehicle = new SpeedData(data.getId(), data.getData1(), data.getTimestamp(), data.getOwner());
			} else if (data instanceof TrafficLightData) {
				locationTrafficLight = ((TrafficLightData) data).getLocation();
				trafficLightStatus = TrafficLightStatus.valueOf(data.getData1());
			}
		}

		AnalyticsEngine engine = new AnalyticsEngine();
		this.previousResult = this.currentResult;
		this.currentResult = engine.runAnalysis(locationVehicle, speedVehicle, locationTrafficLight, trafficLightStatus,
				locationSmartphones);
	}

	public void registerObserver(Observer o) {
		this.observers.add(o);

	}

	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}

	public void notifyObservers(JsonObject jsonResponse) {
		for (Observer o : this.observers) {
			o.notificar(this, jsonResponse);
		}
	}

	public AnalysisResult getResult() {
		return this.currentResult;
	}

	public void monitorTraffic(JsonObject jsonResponse) throws SQLException {

		SensorDB sensorDB = new SensorDB();
		DataDB dataDB = new DataDB();
		ArrayList<ISensing> sensors = sensorDB.readSensors();
		ArrayList<IMobile> mobileList = new ArrayList<IMobile>();
		ArrayList<Data> listData = dataDB.readDatas();

		for (ISensing sensor : sensors) {
			if (sensor.getType().equals("SmartphoneMobile") || sensor.getType().equals("Vehicle")) {
				mobileList.add((IMobile) sensor);
			}
		}

		List<Data> activated = new ArrayList<>();
		for (IMobile m : mobileList) {
			if (!m.getState().equals(DisconnectedState.getInstance())) {
				for (Data d : listData) {
					if (d.getOwner().getId() == m.getId() && d instanceof LocationData) {
						activated.add(d);
						continue;
					}
				}
			}
		}

		for (Data data : listData) {
			if (data instanceof SpeedData || data instanceof TrafficLightData) {
				activated.add(data);
			}
		}

		analyzeTrafficData(activated);

		if (this.currentResult.isCollisionDetected() == true) {
			notifyObservers(jsonResponse);
		}
	}
}
