package com.smarcity.ApplicationLayer;

import com.google.gson.JsonObject;
import com.smarcity.Enum.NotificationType;

public class VehicleNotifier implements Observer {

	private NotificationType notificationType;

	public VehicleNotifier() {
		this.notificationType = NotificationType.SOUND;
	}

	@Override
	public void notificar(TrafficMonitor trafficMonitor, JsonObject jsonResponse) {

		JsonObject mensagem = new JsonObject();
		mensagem.addProperty("notificationType", this.notificationType.toString());
		mensagem.addProperty("message", getDriverMessage(trafficMonitor.getResult().getDetails()));
		mensagem.addProperty("detail", trafficMonitor.getResult().getDetails());
		jsonResponse.addProperty("vehicleNotification", mensagem.toString());
	}

	public static String getDriverMessage(String condition) {
		switch (condition) {
			case "CR":
				return "Freie o veículo! Risco de colisão à frente.";
			case "IBN":
				return "Freie agora! Semáforo vermelho e pedestre se aproximando.";
			case "EN":
				return "Emergência! Colisão com pedestre detectada. Resgate acionado.";
			case "IBNC":
				return "Freie agora! Distância de parada insegura detectada.";
			default:
				return "Condição de risco desconhecida.";
		}
	}
}
