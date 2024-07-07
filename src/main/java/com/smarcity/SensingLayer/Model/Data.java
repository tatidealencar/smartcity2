package com.smarcity.SensingLayer.Model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public class Data implements Serializable {
    private static final long SERIALVERSIONUID = 1L;
    private String data1;
    private String data2;
    private String timestamp;
    private int id;
    private ISensing owner;
    private String sensorType;

    public Data(String data1, String data2, String timestamp, ISensing owner, String sensorType) {
        this.data1 = data1;
        this.data2 = data2;
        this.timestamp = timestamp;
        this.owner = owner;
        this.sensorType = sensorType;
    }

    public String getData1() {
        return this.data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return this.data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ISensing getOwner() {
        return this.owner;
    }

    public String getSensorType() {
        return this.sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    // Método estático para desserializar um array de bytes em um objeto Data
    public Data fromByteArray(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInputStream in = new ObjectInputStream(bis)) {
            return (Data) in.readObject();
        }
    }
}
