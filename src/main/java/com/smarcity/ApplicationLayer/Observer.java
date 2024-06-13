package com.smarcity.ApplicationLayer;

import com.google.gson.JsonObject;

public interface Observer {

	public void notificar(TrafficMonitor trafficMonitor, JsonObject jsonResponse);
}
