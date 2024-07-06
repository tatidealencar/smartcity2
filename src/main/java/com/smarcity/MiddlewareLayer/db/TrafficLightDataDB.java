package com.smarcity.MiddlewareLayer.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrafficLightDataDB {

    private Database db;

    public TrafficLightDataDB() {
        db = Database.getInstance();

        try {
			this.createTrafficLightDataTable();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao criar a tabela Traffic Light Data no banco de dados", e);
		}
    }

    private void createTrafficLightDataTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS trafficLightData (" +
			"id INT AUTO_INCREMENT PRIMARY KEY," +
			"status VARCHAR(255)," +
			"duration VARCHAR(255)," +
			"sensorType VARCHAR(255)," +
			"timestamp VARCHAR(255));";
	
		try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
			statement.execute();
			System.out.println("trafficLightData table created successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
        try { PreparedStatement stmt = db.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeQuery();
        }
        catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao ler LocationData do banco de dados.", e);
		}
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
