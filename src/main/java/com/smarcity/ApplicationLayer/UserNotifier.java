package com.smarcity.ApplicationLayer;

import com.google.gson.JsonObject;
import com.smarcity.Enum.NotificationType;

public class UserNotifier implements Observer {

	private NotificationType notificationType;

	public UserNotifier() {
		this.notificationType = NotificationType.SOUND;
	}

	@Override
	public void notificar(TrafficMonitor trafficMonitor, JsonObject jsonResponse) {
		JsonObject mensagem = new JsonObject();
		mensagem.addProperty("notificationType", this.notificationType.toString());
		mensagem.addProperty("message", getPedestrianMessage(trafficMonitor.getResult().getDetails()));
		mensagem.addProperty("detail", trafficMonitor.getResult().getDetails());
		jsonResponse.addProperty("userNotification", mensagem.toString());
	}

	public static String getPedestrianMessage(String condition) {
		switch (condition) {
			case "CR":
				return "Não atravesse. Risco de atropelamento.";
			case "IBN":
				return "Atenção! Não atravesse! Veículo se aproximando no sinal vermelho.";
			case "EN":
				return "Emergência! Fique parado, resgate a caminho.";
			case "IBNC":
				return "Cuidado! Veículo muito próximo para parar com segurança.";
			default:
				return "Condição de risco desconhecida.";
		}
	}
}
