package com.smarcity.NetworkLayer;

import java.util.HashMap;
import java.util.Map;

import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Model.Data;

public class Router {
	private Map<String, String> routingTable;
	private int bufferSize;

	public Router(int bufferSize) {
		this.bufferSize = bufferSize;
		this.routingTable = new HashMap<>();
	}

	public String routePacket(Data packet, String destination) throws Exception {
		System.out.println("Routing packet to " + destination);
		// Simulando o roteamento
		if (destination.equals("Gateway")) {
			Gateway gateway = new Gateway("192.168.1.1");
			return gateway.forwardDataToCloud(packet);
		}

		return "";
	}

	public Data receivePacket(String origin, ISensing owner, int id, StringBuilder consoleOutput) {
		consoleOutput.append("Getting packet from ").append(origin);
		// Simulando o roteamento
		if (origin.equals("Gateway")) {
			Gateway gateway = new Gateway("192.168.1.1");
			return gateway.fetchDataFromCloud(id, consoleOutput);
		}
		return null;
	}

	public void updateRoutingTable(String node, String address) {
		routingTable.put(node, address);
		System.out.println("Updated routing table: " + node + " -> " + address);
	}
}