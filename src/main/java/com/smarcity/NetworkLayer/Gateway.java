package com.smarcity.NetworkLayer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.smarcity.SensingLayer.Factory.Vehicle;
import com.smarcity.SensingLayer.Interfaces.ISensing;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.LocationData;
import com.smarcity.MiddlewareLayer.db.LocationDataDB;

public class Gateway {
    private String ipAddress;
    private boolean status;

    private static String encryptionKey = "qwertyuiopasdfgh";

    public Gateway(String ipAddress) {
        this.ipAddress = ipAddress;
        this.status = true;
    }

    public String forwardDataToCloud(Data data) throws Exception {
        LocationData locationData = new LocationData(data);
        
        //LocationDataBD salvar
        System.out.println("Forwarded data to cloud: " + data);
        return this.encryptData(data);
    }

    public Data fetchDataFromCloud(int id, StringBuilder consoleOutput) {
        // Traz dados do banco de dados
        LocationDataDB db = new LocationDataDB();
        LocationData data;
        try{ data = db.readLocationData(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao ler LocationData da nuvem.", e);
        }
        // Ou usa mock direto
        // LocationData data = new LocationData("37.774929" ,"-122.419416", "location", "2024-05-22T14:30:00Z", owner, id);
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