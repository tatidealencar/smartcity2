package com.smarcity.MiddlewareLayer;

import java.sql.SQLException;

import com.smarcity.MiddlewareLayer.db.DataDB;
import com.smarcity.SensingLayer.Model.Data;

public class DataProcessor {

	private String apiEndpoint;
	private Data data;

	public DataProcessor(String apiEndpoint) {
		this.apiEndpoint = apiEndpoint;
	}

	public Data getData() {
		return this.data;
	}

	public void sendToCloud() throws SQLException {
		if (data == null) {
			System.out.println("No data to send.");
		} else  {
			DataDB db = new DataDB();
			db.createData(data);
		}

	}

	public void processData(String data) {
		SecurityManager securityManager = new SecurityManager();
		try {
			this.data = securityManager.decryptData(data, Data.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
