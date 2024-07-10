package com.smarcity.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;

import com.smarcity.ApplicationLayer.TrafficMonitor;
import com.smarcity.ApplicationLayer.UserNotifier;
import com.smarcity.ApplicationLayer.VehicleNotifier;
import com.smarcity.Enum.TrafficLightStatus;
import com.smarcity.MiddlewareLayer.DataProcessor;
import com.smarcity.NetworkLayer.NetworkInterface5G;
import com.smarcity.NetworkLayer.NetworkManager;
import com.smarcity.NetworkLayer.Router;
import com.smarcity.SensingLayer.Factory.FactoryActuator;
import com.smarcity.SensingLayer.Factory.FactoryMobile;
import com.smarcity.SensingLayer.Interfaces.IActuator;
import com.smarcity.SensingLayer.Interfaces.IMobile;
import com.smarcity.SensingLayer.Model.Data;
import com.smarcity.SensingLayer.Model.LocationData;
import com.smarcity.SensingLayer.Model.SpeedData;
import com.smarcity.SensingLayer.Model.TrafficLightData;
import com.smarcity.SensingLayer.State.ConnectedState;

@WebServlet(name = "DataServlet", urlPatterns = { "/DataServlet" })
public class DataServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("application/json;charset=UTF-8");

        StringBuilder consoleOutput = new StringBuilder();
        JsonObject jsonResponse = new JsonObject();

        consoleOutput.append("Camada de sensing:");
        consoleOutput.append("Coletando dados dos sensores...");

        String trafficLightLat;
        String trafficLightLng;
        String trafficLightStatus;
        String pedestrianLat;
        String pedestrianLng;
        String carLat;
        String carLng;
        String carSpeed;

        if (request.getParameter("riskOption") != null) {
            switch (request.getParameter("riskOption")) {
                case "CR":
                    trafficLightStatus = "GREEN";
                    trafficLightLat = "-23.5610";
                    trafficLightLng = "-46.6562";
                    pedestrianLat = "-23.5610";
                    pedestrianLng = "-46.6562";
                    carLat = "-23.5610";
                    carLng = "-46.6561";
                    carSpeed = "30";
                    break;
                case "IBN":
                    trafficLightStatus = "RED";
                    trafficLightLat = "-23.5627";
                    trafficLightLng = "-46.6575";
                    pedestrianLat = "-23.5627";
                    pedestrianLng = "-46.6575";
                    carLat = "-23.5628";
                    carLng = "-46.6575";
                    carSpeed = "15";
                    break;
                case "EN":
                    trafficLightStatus = "GREEN";
                    trafficLightLat = "-23.5620";
                    trafficLightLng = "-46.6570";
                    pedestrianLat = "-23.5620";
                    pedestrianLng = "-46.6570";
                    carLat = "-23.5620";
                    carLng = "-46.6570";
                    carSpeed = "10";
                    break;
                case "IBNC":
                    trafficLightStatus = "GREEN";
                    trafficLightLat = "-23.5630";
                    trafficLightLng = "-46.6580";
                    pedestrianLat = "-23.5630";
                    pedestrianLng = "-46.6580";
                    carLat = "-23.5630";
                    carLng = "-46.6582";
                    carSpeed = "120";
                    break;
                default:
                    trafficLightLat = request.getParameter("trafficLightLat");
                    trafficLightLng = request.getParameter("trafficLightLng");
                    trafficLightStatus = request.getParameter("trafficLightStatus");
                    pedestrianLat = request.getParameter("pedestrianLat");
                    pedestrianLng = request.getParameter("pedestrianLng");
                    carLat = request.getParameter("carLat");
                    carLng = request.getParameter("carLng");
                    carSpeed = request.getParameter("carSpeed");
                    break;
            }
        } else {
            trafficLightLat = request.getParameter("trafficLightLat");
            trafficLightLng = request.getParameter("trafficLightLng");
            trafficLightStatus = request.getParameter("trafficLightStatus");
            pedestrianLat = request.getParameter("pedestrianLat");
            pedestrianLng = request.getParameter("pedestrianLng");
            carLat = request.getParameter("carLat");
            carLng = request.getParameter("carLng");
            carSpeed = request.getParameter("carSpeed");
        }

        if (!trafficLightLat.isEmpty() && !trafficLightLng.isEmpty() && !trafficLightStatus.isEmpty()
                && !pedestrianLat.isEmpty()
                && !pedestrianLng.isEmpty() && !carLat.isEmpty() && !carLng.isEmpty()
                && !carSpeed.isEmpty()) {

            NetworkInterface5G networkInterface5G = new NetworkInterface5G("5G", 500, "Tim", 70);
            NetworkManager networkManager = (NetworkManager) NetworkManager.getInstance(consoleOutput);
            networkManager.setNetworkInterface(networkInterface5G);
            Router router = new Router(1024);

            FactoryMobile mobileFactory = new FactoryMobile();
            FactoryActuator actuatorFactory = new FactoryActuator();
            List<Data> listData = new ArrayList<>();
            List<IMobile> mobileList = new ArrayList<>();

            // Vehicle data
            IMobile vehicle = mobileFactory.createVehicle(ConnectedState.getInstance()); // sensor do veículo
            LocationData locationSensorDataVehicle = new LocationData(carLat, carLng, vehicle); // localização do
                                                                                                // veículo
            SpeedData speedSensorDataVehicle = new SpeedData(carSpeed, vehicle);
            listData.add(locationSensorDataVehicle);
            listData.add(speedSensorDataVehicle);
            mobileList.add(vehicle);

            // Smartphone data
            IMobile smartphone = mobileFactory.createSmartphoneMobile(ConnectedState.getInstance());
            LocationData locationSensorDataSmartphone = new LocationData(pedestrianLat, pedestrianLng, smartphone);
            listData.add(locationSensorDataSmartphone);
            mobileList.add(smartphone);

            // Traffic light data
            IActuator trafficLight = actuatorFactory.createTrafficLightActuator();

            LocationData locationTrafficLight = new LocationData(trafficLightLat, trafficLightLng, trafficLight);
            TrafficLightData sensorDataTrafficLight = new TrafficLightData(locationTrafficLight,
                    TrafficLightStatus.valueOf(trafficLightStatus.toUpperCase()), "30", trafficLight);
            listData.add(sensorDataTrafficLight);

            // Início da operação
            networkManager.connect();

            String apiEndpoint = "http://localhost:8080/api/data";
            DataProcessor dataProcessor = new DataProcessor(apiEndpoint);

            for (Data data : listData) {
                dataProcessor.processData(networkManager.sendData(data, router));
                dataProcessor.sendToCloud();
                consoleOutput.append("Data Sent to Cloud: ").append(dataProcessor.getData()).append("\n");
            }

            TrafficMonitor trafficMonitor = TrafficMonitor.getInstance();
            trafficMonitor.removeObservers();
            if (request.getParameter("testOption") != null) {
                if (request.getParameter("testOption").equals("car")) {
                    VehicleNotifier vehicleNotifier = new VehicleNotifier();
                    trafficMonitor.registerObserver(vehicleNotifier);
                } else if (request.getParameter("testOption").equals("pedestrian")) {
                    UserNotifier userNotifier = new UserNotifier();
                    trafficMonitor.registerObserver(userNotifier);
                } else {
                    UserNotifier userNotifier = new UserNotifier();
                    trafficMonitor.registerObserver(userNotifier);
                    VehicleNotifier vehicleNotifier = new VehicleNotifier();
                    trafficMonitor.registerObserver(vehicleNotifier);
                }
            } else {
                UserNotifier userNotifier = new UserNotifier();
                trafficMonitor.registerObserver(userNotifier);
                VehicleNotifier vehicleNotifier = new VehicleNotifier();
                trafficMonitor.registerObserver(vehicleNotifier);
            }
            trafficMonitor.monitorTraffic(jsonResponse);

            // Build JSON response
            jsonResponse.addProperty("trafficLightStatus", trafficLightStatus.toUpperCase());
            jsonResponse.addProperty("collisionRisk", trafficMonitor.getResult().isCollisionDetected());
            jsonResponse.addProperty("consoleOutput", consoleOutput.toString());
            jsonResponse.addProperty("processedData", consoleOutput.toString());

            try (PrintWriter out = response.getWriter()) {
                out.print(jsonResponse.toString());
            }
        }

        // networkManager.disconnect();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServletException | IOException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServletException | IOException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "DataServlet for processing and sending traffic simulation data";
    }
}