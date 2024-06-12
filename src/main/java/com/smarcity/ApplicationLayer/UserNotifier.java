package com.smarcity.ApplicationLayer;

import com.smarcity.Enum.NotificationType;

public class UserNotifier implements Observer {

	private int userId;
	private NotificationType notificationType;

	public UserNotifier(int userId, NotificationType notificationType) {
		this.userId = userId;
		this.notificationType = notificationType;
	}

	@Override
	public void notificar(TrafficMonitor trafficMonitor) {
		System.out.println("Usuário: " + this.userId + " | Tipo de notificação: " + this.notificationType + " | Mensagem: " + trafficMonitor.getResult().getDetails());

	}
}
