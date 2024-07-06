package com.smarcity.MiddlewareLayer.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Model.LocationData;

public class LocationDataDB {

    private Database db;

    // Constructor
    public LocationDataDB() {
        db = Database.getInstance();
        // Try to create the Location table in the database
        try {
			this.createLocationDataTable();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error to create LocationData table.", e);
		}
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

    private void createLocationDataTable() throws SQLException {

		String sql = "CREATE TABLE IF NOT EXISTS location_data ("+
			"id INT AUTO_INCREMENT PRIMARY KEY," +
			"latitude VARCHAR(255)," +
			"longitude VARCHAR(255)," +
			"data_type VARCHAR(255)," +
			"timestamp VARCHAR(255)," +
            "origin VARCHAR(255))";
			// TODO: review
            /* "owner_id INT" +
            "FOREIGN KEY (owner_id) REFERENCES sensing(sensing_id));"; */

		try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
			statement.execute();
		   	System.out.println("locationData table created successfully.");
	  	} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public int createLocationData(LocationData location) throws SQLException {
        String query = "INSERT INTO location_data (latitude, longitude, data_type, timestamp, origin) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, location.getData1());
            stmt.setString(2, location.getData2());
            stmt.setString(3, location.getDataType());
            stmt.setString(4, location.getTimestamp());
            stmt.setString(5, location.getOrigin().toString());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    return insertedId;
                } else {
                    throw new SQLException("Adding location_data failed, no ID obtained.");
                }
            }
        }
    }

    public LocationData readLocationData(int id) throws SQLException {
        String query = "SELECT * FROM location_data WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LocationData location = new LocationData(rs.getString("latitude"), rs.getString("longitude"),
                            rs.getString("data_type"), rs.getString("timestamp"), rs.getString("origin"));
                    return location;
                } else {
                    return null;
                }
            }
        }
    }

    // TODO remove
    public void updateLocationData(LocationData location) throws SQLException {
        String query = "UPDATE location_data SET latitude = ?, longitude = ?, sensor_type = ?, timestamp = ?, owner_id = ? WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, location.getData1());
            stmt.setString(2, location.getData2());
            stmt.setString(3, location.getDataType());
            stmt.setString(4, location.getTimestamp());
            stmt.setString(5, location.getOrigin().toString());
            stmt.executeUpdate();
        }
    }

    public void deleteLocationData(int id) throws SQLException {
        String query = "DELETE FROM location_data WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
