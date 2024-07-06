package com.smarcity.SensingLayer.Model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public abstract class Data implements Serializable {
    private static final long SERIALVERSIONUID = 1L;
    private String latitude;
    private String longitude;
    private String timestamp;
    private int sensingId; // Owner

    public Data(String latitude, String longitude, String timestamp, int sensingId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.sensingId = sensingId;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getSensingId() {
        return this.sensingId;
    }

    public void setSensingId(int sensingId) {
        this.sensingId = sensingId;
    }

    // Método estático para desserializar um array de bytes em um objeto Data
    public Data fromByteArray(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return (Data) in.readObject();
        }
    }
}
