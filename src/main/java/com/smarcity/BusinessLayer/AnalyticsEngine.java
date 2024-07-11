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

            double vehicleLat = Double.parseDouble(locationVehicle.getData1());
            double vehicleLon = Double.parseDouble(locationVehicle.getData2());
            double trafficLightLat = Double.parseDouble(locationTrafficLight.getData1());
            double trafficLightLon = Double.parseDouble(locationTrafficLight.getData2());
            double vehicleSpeed = Double.parseDouble(speedVehicle.getData1()); // Assumindo que a velocidade está em m/s

            for (Data smartphoneLocation : locationSmartphones) {
                double smartphoneLat = Double.parseDouble(smartphoneLocation.getData1());
                double smartphoneLon = Double.parseDouble(smartphoneLocation.getData2());

                double distanceToTrafficLight = calculateDistance(vehicleLat, vehicleLon, trafficLightLat, trafficLightLon);
                double timeToCollision = distanceToTrafficLight / vehicleSpeed;
                double brakingTime = 2.0; // Tempo médio de frenagem em segundos

                boolean isPedestrianCrossing = (smartphoneLat == trafficLightLat) && (Math.abs(vehicleLon - smartphoneLon) <= 30);
                boolean immediateBrakingNeeded = timeToCollision < brakingTime;

                if (immediateBrakingNeeded && vehicleSpeed > 0) {
                    result.setCollisionDetected(true);
                    result.setDetails("CR");
                }

                if (distanceToTrafficLight <= 200 && isPedestrianCrossing) {
                    if (trafficLightStatus == TrafficLightStatus.RED && immediateBrakingNeeded) {
                        result.setCollisionDetected(true);
                        result.setDetails("IBN");
                    } else if (vehicleLon == smartphoneLon) {
                        result.setCollisionDetected(true);
                        result.setDetails("EN");
                    } else if (distanceToTrafficLight <= 30 && immediateBrakingNeeded) {
                        result.setCollisionDetected(true);
                        result.setDetails("IBNC");
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