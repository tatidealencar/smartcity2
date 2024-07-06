package com.smarcity.MiddlewareLayer;

import com.smarcity.MiddlewareLayer.Interfaces.INetworkManager;
import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.SensingLayer.Model.Data;

public class DataProcessor {

	private String apiEndpoint;
	private Data data;
	private INetworkManager manager;

	public DataProcessor(String apiEndpoint) {
		this.apiEndpoint = apiEndpoint;
	}

	public Data getData() {
		return this.data;
	}

	public Data sendToCloud() {
		if (this.data != null) {
			System.out.println("Data sent from DataProcessor to the cloud.");
			return data;
		}
		else {
			System.out.println("No data to send.");
			return null;
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
