package com.smarcity.MiddlewareLayer.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smarcity.SensingLayer.Model.SpeedData;

public class SpeedDataDB {
    private Database db;

    public SpeedDataDB() {
        db = Database.getInstance();

        try {
			this.createSpeedDataTable();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao criar a tabela Speed Data no banco de dados", e);
		}
    }

    private void createSpeedDataTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS SpeedData (" +
			"id INT AUTO_INCREMENT PRIMARY KEY," +
			"velocidade VARCHAR(255)," +
			"unidade VARCHAR(255)," +
			"sensorType VARCHAR(255)," +
			"timestamp VARCHAR(255)," +
            "ownerID INT" +
            "FOREIGN KEY (ownerID) REFERENCES Sensing(SensingId));"; 
	
		try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
			statement.execute();
			System.out.println("SpeedData table created successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public int createSpeedData(SpeedData speed) throws SQLException {
        String query = "INSERT INTO SpeedData (velocidade, unidade, sensorType, timestamp, ownerID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, speed.getLatitude());
            stmt.setString(2, speed.getLongitude());
            stmt.setString(3, speed.getSensorType());
            stmt.setString(4, speed.getTimestamp());
            stmt.setString(5, speed.getTimestamp());
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

    public SpeedData readSpeedData(int id) throws SQLException {
        String query = "SELECT * FROM SpeedData WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    SpeedData speed = new SpeedData(rs.getString("latitude"), rs.getString("longitude"),
                            rs.getString("sensorType"), rs.getString("timestamp"), id, rs.getInt("ownerID");
                    return speed;
                } else {
                    return null; // Or throw an exception if preferred
                }
            }
        }
        catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao ler Speed Data do banco de dados.", e);
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
