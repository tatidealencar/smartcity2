package com.smarcity.MiddlewareLayer.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrafficLightDataDB {

    private Database db;

    public TrafficLightDataDB() {
        db = Database.getInstance();
    }

    public void createTrafficLightData(String status, String duration, String sensorType, String timestamp)
            throws SQLException {
        String query = "INSERT INTO trafficlightdata (status, duration, sensorType, timestamp) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setString(2, duration);
            stmt.setString(3, sensorType);
            stmt.setString(4, timestamp);
            stmt.executeUpdate();
        }
    }

    public ResultSet readTrafficLightData(int id) throws SQLException {
        String query = "SELECT * FROM trafficlightdata WHERE id = ?";
        PreparedStatement stmt = db.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeQuery();
    }

    public void updateTrafficLightData(int id, String status, String duration, String sensorType, String timestamp)
            throws SQLException {
        String query = "UPDATE trafficlightdata SET status = ?, duration = ?, sensorType = ?, timestamp = ? WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setString(2, duration);
            stmt.setString(3, sensorType);
            stmt.setString(4, timestamp);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }

    public void deleteTrafficLightData(int id) throws SQLException {
        String query = "DELETE FROM trafficlightdata WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
