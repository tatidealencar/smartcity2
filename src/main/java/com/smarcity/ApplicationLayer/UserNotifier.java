package com.smarcity.ApplicationLayer;

import com.google.gson.JsonObject;
import com.smarcity.Enum.NotificationType;

public class UserNotifier implements Observer {

	private NotificationType notificationType;

	public UserNotifier() {
		this.notificationType = NotificationType.HAPTIC;
	}

	@Override
	public void notificar(TrafficMonitor trafficMonitor, JsonObject jsonResponse) {
		JsonObject mensagem = new JsonObject();
		mensagem.addProperty("notificationType", this.notificationType.toString());
		mensagem.addProperty("message", trafficMonitor.getResult().getDetails());
		jsonResponse.addProperty("userNotification", mensagem.toString());
	}
}
