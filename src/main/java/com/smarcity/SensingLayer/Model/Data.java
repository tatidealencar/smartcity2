package com.smarcity.SensingLayer.Model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Data implements Serializable {
    private static final long SERIALVERSIONUID = 1L;
    private String data1;
    private String data2;
    private String timestamp;
    private int sensorId;

    public Data(String data1, String data2, String timestamp, int id) {
        this.data1 = data1;
        this.data2 = data2;
        this.timestamp = timestamp;
        this.sensorId = id;
    }

    public String getData1() {
        return this.data1;
    }

    public void setLData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return this.data2;
    }

    public void setLData2(String data2) {
        this.data2 = data2;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getSensorId() {
        return this.sensorId;
    }

    // Método estático para desserializar um array de bytes em um objeto Data
    public Data fromByteArray(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return (Data) in.readObject();
        }
    }
}
