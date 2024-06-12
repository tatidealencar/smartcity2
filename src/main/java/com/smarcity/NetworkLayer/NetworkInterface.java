package com.smarcity.NetworkLayer;

public class NetworkInterface {

	private String protocol;
	private int bandwidth;

	public NetworkInterface(String protocol, int bandwidth) {
		this.protocol = protocol;
		this.bandwidth = bandwidth;
	}

	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getBandwidth() {
		return this.bandwidth;
	}

	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
}
