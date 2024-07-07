package com.smarcity.MiddlewareLayer.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.smarcity.Enum.TrafficLightStatus;
import com.smarcity.SensingLayer.Factory.FactoryActuator;
import com.smarcity.SensingLayer.Factory.FactoryMobile;
import com.smarcity.SensingLayer.Factory.FactorySensor;
import com.smarcity.SensingLayer.Factory.TrafficLightActuator;
import com.smarcity.SensingLayer.Interfaces.IActuator;
import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Interfaces.ISensor;
import com.smarcity.SensingLayer.State.MobileState;

public class SensorDB {

    private Database db;

    public SensorDB() {
        db = Database.getInstance();
    }

    public int createSensor(ISensing sensor) throws SQLException {
        String query = "";

        if (sensor instanceof ISensor) {
            query = "INSERT INTO sensor (type) VALUES (?)";
        } else if (sensor instanceof IMobile) {
            query = "INSERT INTO sensor (type, state) VALUES (?, ?)";
        } else if (sensor instanceof IActuator) {
            query = "INSERT INTO sensor (type, traffic_light_status) VALUES (?, ?)";
        }

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, sensor.getType());

            if (sensor instanceof IMobile) {
                IMobile mobile = (IMobile) sensor;
                stmt.setString(2, mobile.getState().toString());
            } else if (sensor instanceof IActuator) {
                TrafficLightActuator actuactor = (TrafficLightActuator) sensor;
                stmt.setString(2, actuactor.getStatus().toString());
            }

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    return insertedId;
                } else {
                    throw new SQLException("Creating locationdata failed, no ID obtained.");
                }
            }
        }
    }

    public ISensing readSensor(int id) throws SQLException {
        String query = "SELECT * FROM sensor WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    if (rs.getString("type").equals("LocationSensor") || rs.getString("type").equals("SpeedSensor")) {
                        FactorySensor factory = new FactorySensor();
                        ISensor sensor = null;
                        if (rs.getString("type").equals("LocationSensor")) {
                            sensor = factory.createLocationSensor();
                        } else {
                            sensor = factory.createSpeedSensor();
                        }
                        sensor.getSensor(id);
                        return sensor;
                    } else if (rs.getString("type").equals("SmartphoneMobile")
                            || rs.getString("type").equals("Vehicle")) {
                        FactoryMobile factory = new FactoryMobile();
                        IMobile sensor = null;
                        if (rs.getString("type").equals("SmartphoneMobile")) {
                            sensor = factory.createSmartphoneMobile(id);
                        } else {
                            sensor = factory.createVehicle(id);
                        }
                        sensor.setId(id);
                        sensor.setState(MobileState.getState(rs.getString("state")));
                        return sensor;
                    } else if (rs.getString("type").equals("TrafficLightActuator")) {
                        FactoryActuator factory = new FactoryActuator();
                        IActuator sensor = factory.createTrafficLightActuator();
                        sensor.collectData(TrafficLightStatus.valueOf(rs.getString("traffic_light_status")), id);
                        return sensor;
                    }
                } else {
                    return null; 
                }
            }
        }
        return null;
    }

    public ArrayList<ISensing> readSensors() throws SQLException {
        String query = "SELECT * FROM sensor";
    
        ArrayList<ISensing> list = new ArrayList<>();
    
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("type").equals("LocationSensor") || rs.getString("type").equals("SpeedSensor")) {
                        FactorySensor factory = new FactorySensor();
                        ISensor sensor = null;
                        if (rs.getString("type").equals("LocationSensor")) {
                            sensor = factory.createLocationSensor(rs.getInt("id"));
                        } else {
                            sensor = factory.createSpeedSensor(rs.getInt("id"));
                        }
                        sensor.getSensor(rs.getInt("id"));
                        list.add(sensor);
                    } else if (rs.getString("type").equals("SmartphoneMobile") || rs.getString("type").equals("Vehicle")) {
                        FactoryMobile factory = new FactoryMobile();
                        IMobile sensor = null;
                        if (rs.getString("type").equals("SmartphoneMobile")) {
                            sensor = factory.createSmartphoneMobile(rs.getInt("id"));
                        } else {
                            sensor = factory.createVehicle(rs.getInt("id"));
                        }
                        sensor.setState(MobileState.getState(rs.getString("state")));
                        list.add(sensor);
                    } else if (rs.getString("type").equals("TrafficLightActuator")) {
                        FactoryActuator factory = new FactoryActuator();
                        IActuator sensor = factory.createTrafficLightActuator();
                        sensor.collectData(TrafficLightStatus.valueOf(rs.getString("traffic_light_status")), rs.getInt("id"));
                        list.add(sensor);
                    }
                }
            }
        }
        return list;
    }
}
