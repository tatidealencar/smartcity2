package com.smarcity.ApplicationLayer;

import com.google.gson.JsonObject;
import com.smarcity.Enum.NotificationType;

public class UserNotifier implements Observer {

	private int userId;
	private NotificationType notificationType;

	public UserNotifier(int userId, NotificationType notificationType) {
		this.userId = userId;
		this.notificationType = notificationType;
	}

	@Override
	public void notificar(TrafficMonitor trafficMonitor, JsonObject jsonResponse) {

		JsonObject mensagem = new JsonObject();
		mensagem.addProperty("useId", this.userId);
		mensagem.addProperty("notificationType", this.notificationType.toString());
		mensagem.addProperty("message", trafficMonitor.getResult().getDetails());
		jsonResponse.addProperty("userNotification", mensagem.toString());
		
		//System.out.println("Usuário: " + this.userId + " | Tipo de notificação: " + this.notificationType + " | Mensagem: " + trafficMonitor.getResult().getDetails());

	}
}
