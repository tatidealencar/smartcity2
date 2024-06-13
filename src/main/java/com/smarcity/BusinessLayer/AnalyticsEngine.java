package com.smarcity.BusinessLayer;

import java.util.List;

import com.smarcity.Enum.TrafficLightStatus;
import com.smarcity.SensingLayer.Model.Data;

public class AnalyticsEngine {

	public AnalysisResult runAnalysis(Data locationVehicle, Data speedVehicle, Data locationTrafficLight,
			TrafficLightStatus trafficLightStatus, List<Data> locationSmartphones) {

		AnalysisResult result = new AnalysisResult(false, "No collision detected");

		if (locationVehicle != null && speedVehicle != null && locationTrafficLight != null
				&& trafficLightStatus != null) {

			for (Data smartphoneLocation : locationSmartphones) {
				double vehicleLat = Double.parseDouble(locationVehicle.getData1());
				double vehicleLon = Double.parseDouble(locationVehicle.getData2());
				double trafficLightLat = Double.parseDouble(locationTrafficLight.getData1());
				double trafficLightLon = Double.parseDouble(locationTrafficLight.getData2());
				double smartphoneLat = Double.parseDouble(smartphoneLocation.getData1());
				double smartphoneLon = Double.parseDouble(smartphoneLocation.getData2());

				// Verifica se carro, smartphone e semáforo estão na mesma localização
				boolean sameLocation = vehicleLat == trafficLightLat && vehicleLon == trafficLightLon
						&& vehicleLat == smartphoneLat && vehicleLon == smartphoneLon;

				if (sameLocation) {
					if (trafficLightStatus == TrafficLightStatus.RED) {
						result.setCollisionDetected(false);
						result.setDetails("No collision detected due to red light");
					} else {
						result.setCollisionDetected(true);
						result.setDetails("Collision risk detected");
					}
				} else {
					double distanceVehicleToTrafficLight = calculateDistance(vehicleLat, vehicleLon, trafficLightLat,
							trafficLightLon);
					/*
					 * double distanceSmartphoneToTrafficLight = calculateDistance(smartphoneLat,
					 * smartphoneLon,
					 * trafficLightLat, trafficLightLon);
					 */

					if (distanceVehicleToTrafficLight > 100) {
						result.setCollisionDetected(false);
						result.setDetails("No collision detected due to distance");
					} else {
						if (trafficLightStatus == TrafficLightStatus.RED) {
							result.setCollisionDetected(false);
							result.setDetails("No collision detected due to red light");
						} else {
							result.setCollisionDetected(true);
							result.setDetails("Collision risk detected due to proximity and light status");
						}
					}
				}
			}
		}

		return result;
	}

	private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		final int R = 6371; // Raio da Terra em quilômetros

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
						* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return R * c * 1000; // Converte para metros
	}
}
