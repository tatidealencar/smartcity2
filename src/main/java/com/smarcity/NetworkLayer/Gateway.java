package com.smarcity.NetworkLayer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.LocationData;

public class Gateway {
    private String ipAddress;
    private boolean status;

    private static String encryptionKey = "qwertyuiopasdfgh";

    public Gateway(String ipAddress) {
        this.ipAddress = ipAddress;
        this.status = true;
    }

    public String forwardDataToCloud(Data data) throws Exception {
        System.out.println("Forwarding data to cloud: " + data);
        return this.encryptData(data);
    }

    public Data fetchDataFromCloud(ISensing owner, int id, StringBuilder consoleOutput) {
        LocationData data = new LocationData("37.774929" ,"-122.419416", owner);
        consoleOutput.append(data);
        return data;
    }

    public String encryptData(Data data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] serializedData = serialize(data);
        byte[] encryptedBytes = cipher.doFinal(serializedData);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private byte[] serialize(Data data) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(data);
            return bos.toByteArray();
        }
    }
}