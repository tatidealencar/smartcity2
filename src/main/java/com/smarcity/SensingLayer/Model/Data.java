package com.smarcity.SensingLayer.Model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.smarcity.SensingLayer.Interfaces.ISensing;

public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    private String data1;
    private String data2;
    private String timestamp;
    /* origin is the origin of the object that is the origin for the data,
    it is an ISensing that can be: 
     * Vehicle, SmartphoneMobile (IMobile)
     * TrafficLightActuator (IActuator)
     * LocationSensor, SpeedSensor (ISensor)
     */
    private ISensing origin;
    /* dataType can be: 
        LocationData        "location"
        SpeedData           "speed"
        TrafficLightData    "trafficlight"
    */
    private String dataType; 

    public Data(String data1, String data2, String timestamp, String dataType, ISensing origin) {
        this.data1 = data1;
        this.data2 = data2;
        this.timestamp = timestamp;
        this.origin = origin;
    }

    public Data(String data1, String data2, String timestamp, String dataType, String originString) {
        this.data1 = data1;
        this.data2 = data2;
        this.timestamp = timestamp;
        //this.origin = origin;
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

    public ISensing getOrigin() {
        return this.origin;
    }

    public void setOrigin(ISensing origin) {
        this.origin = origin;
    }

    public String getDataType() {
        return dataType
        ;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    // Método estático para desserializar um array de bytes em um objeto Data
    public Data fromByteArray(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return (Data) in.readObject();
        }
    }
}
