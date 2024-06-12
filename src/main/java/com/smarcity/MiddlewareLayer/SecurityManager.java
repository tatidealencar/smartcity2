package com.smarcity.MiddlewareLayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.smarcity.SensingLayer.Model.Data;

public class SecurityManager {

	private String encryptionKey;

	public SecurityManager() {
		this.encryptionKey = "qwertyuiopasdfgh";
	}

	public Data decryptData(String encryptedData, Class<? extends Data> clazz) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec keySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);

		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
		return deserialize(decryptedBytes, clazz);
	}

	private byte[] serialize(Data data) throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(bos)) {
			out.writeObject(data);
			return bos.toByteArray();
		}
	}

	private Data deserialize(byte[] bytes, Class<? extends Data> clazz)
			throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				ObjectInputStream in = new ObjectInputStream(bis)) {
			return (Data) in.readObject();
		}
	}

}
