package com.smarcity.MiddlewareLayer.Interfaces;

import com.smarcity.NetworkLayer.NetworkInterface;
import com.smarcity.NetworkLayer.Router;
import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Model.Data;

public interface INetworkManager {
    public void connect();

    public void disconnect();

    public String sendData(Data data, Router router) throws Exception;

    public Data receiveData(Router router, ISensing owner, int id);

    public void setNetworkInterface(NetworkInterface network);
}
