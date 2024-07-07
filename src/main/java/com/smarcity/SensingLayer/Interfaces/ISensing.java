package com.smarcity.SensingLayer.Interfaces;

import java.io.Serializable;

import com.smarcity.NetworkLayer.NetworkManager;

public interface ISensing extends Serializable {
    
    public void sendData(NetworkManager networkManager);

    public String getType();

    public int getId();
}
