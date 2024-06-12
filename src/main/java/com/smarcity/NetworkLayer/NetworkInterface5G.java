package com.smarcity.NetworkLayer;

public class NetworkInterface5G extends NetworkInterface {

	private String carrier;
	private int signalStrength;
	private String type;

	public NetworkInterface5G(String protocol, int bandwidth, String carrier, int signalStrength) {
		super(protocol, bandwidth);
		this.carrier = carrier;
		this.signalStrength = signalStrength;
		this.type = "5G";
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public void setSignalStrength(int signalStrength) {
		this.signalStrength = signalStrength;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCarrier() {
		return this.carrier;
	}

	public int getSignalStrength() {
		return this.signalStrength;
	}

	public String getType() {
		return this.type;
	}
}
