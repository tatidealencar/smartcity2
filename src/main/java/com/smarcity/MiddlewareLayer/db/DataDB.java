package com.smarcity.MiddlewareLayer.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.smarcity.Enum.TrafficLightStatus;
import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.LocationData;
import com.smarcity.SensingLayer.Model.SpeedData;
import com.smarcity.SensingLayer.Model.TrafficLightData;

public class DataDB {

    private Database db;

    public DataDB() {
        db = Database.getInstance();
    }

    public int createData(Data data) throws SQLException {
        String query = "";
        if (data instanceof TrafficLightData) {
            query = "INSERT INTO data (data1, data2, sensorType, owner, location) VALUES (?, ?, ?, ?, ?)";
        } else {
            query = "INSERT INTO data (data1, data2, sensorType, owner) VALUES (?, ?, ?, ?)";
        }

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getData1());
            stmt.setString(2, data.getData2());
            stmt.setString(3, data.getSensorType());
            stmt.setInt(4, data.getOwner().getId());

            if (data instanceof TrafficLightData) {
                TrafficLightData trafficLightData = (TrafficLightData) data;
                int idTrafficLightData = this.createData(trafficLightData.getLocation());
                stmt.setInt(5, idTrafficLightData);
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

    public Data readData(int id) throws SQLException {
        String query = "SELECT * FROM data WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    SensorDB db2 = new SensorDB();
                    ISensing sensing = db2.readSensor(rs.getInt("owner"));

                    if (rs.getString("sensorType").equals("location")) {
                        LocationData location = new LocationData(id, rs.getString("data1"), rs.getString("data2"),
                                rs.getString("timestamp"), sensing);
                        return location;
                    } else if (rs.getString("sensorType").equals("speed")) {
                        SpeedData speed = new SpeedData(id, rs.getString("data1"), rs.getString("timestamp"), sensing);
                        return speed;
                    } else if (rs.getString("sensorType").equals("trafficlight")) {
                        Data location = this.readData(rs.getInt("location"));
                        TrafficLightData trafficlight = new TrafficLightData(id, location,
                                TrafficLightStatus.valueOf(rs.getString("data1")), rs.getString("data2"),
                                rs.getString("timestamp"), sensing);
                        return trafficlight;
                    }
                } else {
                    return null; // Or throw an exception if preferred
                }
            }
        }
        return null;
    }

    public ArrayList<Data> readDatas() throws SQLException {
        ArrayList<Data> list = new ArrayList<>();

        String query = "SELECT d.*\n" +
                "FROM data d\n" +
                "INNER JOIN (\n" +
                "    SELECT sensorType, MAX(timestamp) as max_timestamp\n" +
                "    FROM data\n" +
                "    GROUP BY sensorType\n" +
                ") subquery\n" +
                "ON d.sensorType = subquery.sensorType AND d.timestamp = subquery.max_timestamp\n";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SensorDB db2 = new SensorDB();
                    ISensing sensing = db2.readSensor(rs.getInt("owner"));

                    if (rs.getString("sensorType").equals("location")) {
                        LocationData location = new LocationData(rs.getInt("id"), rs.getString("data1"),
                                rs.getString("data2"),
                                rs.getString("timestamp"), sensing);
                        list.add(location);
                    } else if (rs.getString("sensorType").equals("speed")) {
                        SpeedData speed = new SpeedData(rs.getInt("id"), rs.getString("data1"),
                                rs.getString("timestamp"), sensing);
                        list.add(speed);
                    } else if (rs.getString("sensorType").equals("trafficlight")) {
                        Data location = this.readData(rs.getInt("location"));
                        TrafficLightData trafficlight = new TrafficLightData(rs.getInt("id"), location,
                                TrafficLightStatus.valueOf(rs.getString("data1")), rs.getString("data2"),
                                rs.getString("timestamp"), sensing);
                        list.add(trafficlight);
                    }
                }
            }
        }
        return list; // Return the list, empty if no data was found
    }

}
