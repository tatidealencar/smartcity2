package com.smarcity.SensingLayer.Model;

import com.smarcity.SensingLayer.Factory.Vehicle;
import com.smarcity.SensingLayer.Interfaces.ISensing;

public class LocationData extends Data {
    private static final long serialVersionUID = 1L;

    public LocationData(String latitude, String longitude, String dataType, String timestamp, ISensing origin) {
        super(latitude, longitude, dataType, timestamp, origin);
        super.setDataType(dataType);
    }

    public LocationData(String latitude, String longitude, String dataType, String timestamp, String originString) {
        super(latitude, longitude, dataType, timestamp, originString);
        super.setDataType(dataType);
    }

    public String getDataType() {
        return super.getDataType();
    }

    public void setDataType(String dataType) {
        super.setDataType(dataType);
    }

    public String toString() {
        return super.getDataType() + ": " + super.getData1() + " - " + super.getData2() + " - " + super.getTimestamp()
        + " - id: " + super.getOrigin();
    }

    public void setOrigin(ISensing origin) {
        super.setOrigin(origin);
    }

    public void setLatitude(String latitude) {
        super.setLData1(latitude);
    }

    public void setLongitude(String longitude) {
        super.setLData2(longitude);
    }

    public void setOwner(String owner) {
        setOwner(owner);
    }

    public ISensing getOrigin() {
        return super.getOrigin();
    }
}