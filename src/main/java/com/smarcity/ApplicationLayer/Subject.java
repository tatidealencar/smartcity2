package com.smarcity.ApplicationLayer;

import com.google.gson.JsonObject;

public interface Subject {

	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObservers(JsonObject jsonResponse);

}
