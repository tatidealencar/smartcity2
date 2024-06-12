package com.smarcity.NetworkLayer;

import java.util.List;

import com.smarcity.MiddlewareLayer.Interfaces.INetworkManager;
import com.smarcity.SensingLayer.Interfaces.IActuator;
import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Interfaces.ISensor;
import com.smarcity.SensingLayer.Model.Data;

public class NetworkManager implements INetworkManager {

	private static INetworkManager instance = null;
	private NetworkInterface network;
	private List<ISensor> sensors;
	private List<IActuator> actuatores;
	private List<IMobile> mobiles;

	private NetworkManager() {}

	public static INetworkManager getInstance() {
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}

	public void connect() {
		System.out.println("Connecting using " + network.getProtocol() + " with bandwidth " + network.getBandwidth());
	}

	public void disconnect() {
		System.out.println("Disconnecting...");
	}

	public String sendData(Data data, Router router) {
		System.out.println("Sending data: " + data);
		try {
			return router.routePacket(data, "Gateway");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public Data receiveData(Router router, ISensing owner, int id) {
		System.out.println("Getting data");
		return router.receivePacket("Gateway", owner, id);
	}

	public NetworkInterface getNetwork() {
		return this.network;
	}

	@Override
	public void setNetworkInterface(NetworkInterface network) {
		this.network = network;
	}
}
