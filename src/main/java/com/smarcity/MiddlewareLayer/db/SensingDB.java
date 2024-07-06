package com.smarcity.MiddlewareLayer.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Model.LocationData;

public class SensingDB {

    private Database db;

    public SensingDB() {
        db = Database.getInstance();
        try {
			this.createLocationDataTable();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao criar tabela Sensing no banco de dados.", e);
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

    private void createSensingTable() throws SQLException {

		String sql = "CREATE TABLE IF NOT EXISTS Sensing ("+
			"id INT AUTO_INCREMENT PRIMARY KEY," +
			"type VARCHAR(255);";

		try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
			statement.execute();
		   	System.out.println("locationData table created successfully.");
	  	} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public int insertLocationData(LocationData location) throws SQLException {
        String query = "INSERT INTO locationdata (latitude, longitude, sensorType, owner, trafficLightId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, location.getLatitude());
            stmt.setString(2, location.getLongitude());
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
            catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao ler LocationData do banco de dados. [2]", e);
            }
        }
        catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao ler LocationData do banco de dados. [1]", e);
		}
    }

    public void updateLocationData(LocationData location) throws SQLException {
        String query = "UPDATE locationdata SET latitude = ?, longitude = ?, sensorType = ?, owner = ?, trafficLightId = ? WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, location.getLatitude());
            stmt.setString(2, location.getLongitude());
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

