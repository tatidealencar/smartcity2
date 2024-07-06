package com.smarcity.MiddlewareLayer.db;

import com.smarcity.ApplicationLayer.Interfaces.IDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database implements IDataBase {
	private static Database instance;
	private Connection connection;

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/smartcity";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	// Return database instance (singleton)
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	private Database() {
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error connecting to database.", e);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public ResultSet executeQuery(String query) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(query);
		return statement.executeQuery();
	}

	public int executeUpdate(String query) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(query);
		return statement.executeUpdate();
	}

	public void close() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}