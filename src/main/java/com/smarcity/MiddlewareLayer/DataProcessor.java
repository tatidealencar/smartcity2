package com.smarcity.MiddlewareLayer;

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

	public Data sendToCloud() {
		if (data == null) {
			System.out.println("No data to send.");
		}

		System.out.println("Data sent successfully.");
		return data;
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
