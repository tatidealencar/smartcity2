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
	StringBuilder consoleOutput;

	private NetworkManager(StringBuilder consoleOutput) {
		this.consoleOutput = consoleOutput;

	}

	public static INetworkManager getInstance(StringBuilder consoleOutput) {
		if (instance == null) {
			instance = new NetworkManager(consoleOutput);
		}
		return instance;
	}

	public void connect( ) {
		consoleOutput.append("Connecting using ").append(network.getProtocol()).append(" with bandwidth "). append(network.getBandwidth());
	}

	public void disconnect() {
		consoleOutput.append("Disconnecting...");
	}

	public String sendData(Data data, Router router) {
		consoleOutput.append("Sending data: ").append(data);
		try {
			return router.routePacket(data, "Gateway");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public Data receiveData(Router router, ISensing owner, int id) {
		System.out.println("Getting data");
		return router.receivePacket("Gateway", owner, id, consoleOutput);
	}

	public NetworkInterface getNetwork() {
		return this.network;
	}

	@Override
	public void setNetworkInterface(NetworkInterface network) {
		this.network = network;
	}
}
