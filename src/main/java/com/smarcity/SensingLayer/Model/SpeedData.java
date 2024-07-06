package com.smarcity.SensingLayer.Model;
import com.smarcity.SensingLayer.Interfaces.ISensing;

public class SpeedData extends Data {
    private static final long serialVersionUID = 1L; 

    public SpeedData(String velocidade, String unidade, String dataType, String timestamp, ISensing origin) {
        super(velocidade, unidade, timestamp, dataType, origin);
    }

   public String getDataType() {
        return super.getDataType();
    }

    public void setDataType(String dataType) {
        super.setDataType(dataType);
    }

    public String toString() {
        return super.getDataType() + ": " + super.getData1() + " - " + super.getData2() + " - " + super.getTimestamp();
    }
}