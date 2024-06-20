package com.smarcity.MiddlewareLayer.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smarcity.SensingLayer.Model.SpeedData;

public class SpeedDataDB {
    private Database db;

    public SpeedDataDB() {
        db = Database.getInstance();
    }

    public int createSpeedData(SpeedData speed) throws SQLException {
        String query = "INSERT INTO speeddata (velocidade, unidade, sensorType, timestamp) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, speed.getData1());
            stmt.setString(2, speed.getData2());
            stmt.setString(3, speed.getSensorType());
            stmt.setString(4, speed.getTimestamp());
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

    public SpeedData readLocationData(int id) throws SQLException {
        String query = "SELECT * FROM speeddata WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    SpeedData speed = new SpeedData(rs.getString("latitude"), rs.getString("longitude"),
                            rs.getString("sensorType"), rs.getString("timestamp"), id);
                    return speed;
                } else {
                    return null; // Or throw an exception if preferred
                }
            }
        }
    }

    public void updateSpeedData(int id, String velocidade, String unidade, String sensorType, String timestamp) throws SQLException {
        String query = "UPDATE speeddata SET velocidade = ?, unidade = ?, sensorType = ?, timestamp = ? WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, velocidade);
            stmt.setString(2, unidade);
            stmt.setString(3, sensorType);
            stmt.setString(4, timestamp);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }

    public void deleteSpeedData(int id) throws SQLException {
        String query = "DELETE FROM speeddata WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
