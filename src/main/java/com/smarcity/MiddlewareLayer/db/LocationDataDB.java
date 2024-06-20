package com.smarcity.MiddlewareLayer.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Model.LocationData;

public class LocationDataDB {

    private Database db;

    public LocationDataDB() {
        db = Database.getInstance();
    }

    public ISensing parseSensing(String ownerClassName) {
        try {
            Class<?> clazz = Class.forName(ownerClassName);
            return (ISensing) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int createLocationData(LocationData location) throws SQLException {
        String query = "INSERT INTO locationdata (latitude, longitude, sensorType, owner, trafficLightId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, location.getData1());
            stmt.setString(2, location.getData2());
            stmt.setString(3, location.getSensorType());
            stmt.setString(4, location.getOwner().getClass().getName());
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

    public LocationData readLocationData(int id) throws SQLException {
        String query = "SELECT * FROM locationdata WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LocationData location = new LocationData(rs.getString("latitude"), rs.getString("longitude"),
                            rs.getString("sensorType"), rs.getString("timestamp"), parseSensing(rs.getString("owner")),
                            id);
                    return location;
                } else {
                    return null; // Or throw an exception if preferred
                }
            }
        }
    }

    public void updateLocationData(LocationData location) throws SQLException {
        String query = "UPDATE locationdata SET latitude = ?, longitude = ?, sensorType = ?, owner = ?, trafficLightId = ? WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, location.getData1());
            stmt.setString(2, location.getData2());
            stmt.setString(3, location.getSensorType());
            stmt.setString(4, location.getOwner().getClass().getName());
            stmt.setInt(6, location.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteLocationData(int id) throws SQLException {
        String query = "DELETE FROM locationdata WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
