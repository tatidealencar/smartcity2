<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>SmartCity Simulator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="./style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous">
    </script>
</head>

<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Simulador de Controle de Tráfego</h1>

        <form id="simulation-form">
            <div class="row mb-3">
                <div class="col">
                    <label for="traffic-light-lat" class="form-label">Latitude do Semáforo</label>
                    <input type="text" class="form-control" id="traffic-light-lat" name="trafficLightLat"
                        placeholder="Ex.: -23.55052">
                </div>
                <div class="col">
                    <label for="traffic-light-lng" class="form-label">Longitude do Semáforo</label>
                    <input type="text" class="form-control" id="traffic-light-lng" name="trafficLightLng"
                        placeholder="Ex.: -46.633308">
                </div>
                <div class="col">
                    <label for="traffic-light-status" class="form-label">Status Inicial do Semáforo</label>
                    <select class="form-control" id="traffic-light-status" name="trafficLightStatus">
                        <option value="RED">Vermelho</option>
                        <option value="YELLOW">Amarelo</option>
                        <option value="GREEN">Verde</option>
                    </select>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <label for="pedestrian-lat" class="form-label">Latitude do Pedestre</label>
                    <input type="text" class="form-control" id="pedestrian-lat" name="pedestrianLat"
                        placeholder="Ex.: -23.55052">
                </div>
                <div class="col">
                    <label for="pedestrian-lng" class="form-label">Longitude do Pedestre</label>
                    <input type="text" class="form-control" id="pedestrian-lng" name="pedestrianLng"
                        placeholder="Ex.: -46.633308">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <label for="car-lat" class="form-label">Latitude do Carro</label>
                    <input type="text" class="form-control" id="car-lat" name="carLat" placeholder="Ex.: -23.55052">
                </div>
                <div class="col">
                    <label for="car-lng" class="form-label">Longitude do Carro</label>
                    <input type="text" class="form-control" id="car-lng" name="carLng" placeholder="Ex.: -46.633308">
                </div>
                <div class="col">
                    <label for="car-speed" class="form-label">Velocidade do Carro</label>
                    <input type="number" class="form-control" id="car-speed" name="carSpeed" placeholder="Ex.: 100">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <label for="test-options" class="form-label">Escolha uma opção de teste:</label>
                    <div id="test-options">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="testOption" id="car-test" value="car">
                            <label class="form-check-label" for="car-test">
                                Testar carro
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="testOption" id="pedestrian-test"
                                value="pedestrian">
                            <label class="form-check-label" for="pedestrian-test">
                                Testar pedestre
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="testOption" id="both-test" value="both">
                            <label class="form-check-label" for="both-test">
                                Testar ambos
                            </label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <label for="risk-options" class="form-label">Escolha a condição de risco:</label>
                    <div id="risk-options">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="riskOption" id="cr-test" value="CR">
                            <label class="form-check-label" for="cr-test">
                                Risco de colisão (CR): Frenagem imediata necessária para evitar uma colisão potencial
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="riskOption" id="ibn-test" value="IBN">
                            <label class="form-check-label" for="ibn-test">
                                Frenagem imediata necessária (IBN): Risco de colisão devido ao semáforo vermelho e
                                proximidade
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="riskOption" id="en-test" value="EN">
                            <label class="form-check-label" for="en-test">
                                Emergência necessária (EN): Veículo e pedestre na mesma posição
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="riskOption" id="ibnc-test" value="IBNC">
                            <label class="form-check-label" for="ibnc-test">
                                Frenagem imediata necessária (IBNC): Veículo muito próximo para parar com segurança
                            </label>
                        </div>
                    </div>
                </div>
            </div>

            <button type="button" id="simulate-button" class="btn btn-primary">Simular</button>
        </form>

        <div class="notifications mt-3">
            <div id="notification-danger" class="alert alert-danger d-none">Atenção! Risco de colisão detectado!</div>
            <div id="notification-safe" class="alert alert-success d-none">Nenhum risco de colisão.</div>
            <div id="notification-none" class="alert alert-info d-none">Nenhuma notificação.</div>
        </div>

        <div class="simulation-container">
            <div class="device smartphone">
                <h4 class="text-center">Smartphone</h4>
                <div id="smartphone-notification" class="alert alert-info">Nenhuma notificação</div>
            </div>
            <div class="device car-dashboard">
                <div class="dashboard-title">Painel do Carro</div>
                <div id="vehicle-notification" class="notification alert-info">Nenhuma notificação</div>
            </div>
            <div class="traffic-light">
                <div class="light red" id="traffic-light-red"></div>
                <div class="light yellow" id="traffic-light-yellow"></div>
                <div class="light green" id="traffic-light-green"></div>
            </div>
            <audio id="CR_driver" src="audio/driver/CR.mp3" type="audio/mp3"></audio>
            <audio id="EN_driver" src="audio/driver/EN.mp3" type="audio/mp3"></audio>
            <audio id="IBN_driver" src="audio/driver/IBN.mp3" type="audio/mp3"></audio>
            <audio id="IBNC_driver" src="audio/driver/IBNC.mp3" type="audio/mp3"></audio>
            <audio id="CR_pedestrian" src="audio/pedestrian/CR.mp3" type="audio/mp3"></audio>
            <audio id="EN_pedestrian" src="audio/pedestrian/EN.mp3" type="audio/mp3"></audio>
            <audio id="IBN_pedestrian" src="audio/pedestrian/IBN.mp3" type="audio/mp3"></audio>
            <audio id="IBNC_pedestrian" src="audio/pedestrian/IBNC.mp3" type="audio/mp3"></audio>

        </div>

        <div class="mt-3">
            <h4>Console de Saída do Sistema</h4>
            <div class="console">
                <div id="console-output" class="console-output"></div>
            </div>
        </div>
    </div>
    <script>
        var CR_driver = document.getElementById('CR_driver');
        CR_driver.volume = 1.0;
        CR_driver.muted = false;

        var EN_driver = document.getElementById('EN_driver');
        EN_driver.volume = 1.0;
        EN_driver.muted = false;

        var IBN_driver = document.getElementById('IBN_driver');
        IBN_driver.volume = 1.0;
        IBN_driver.muted = false;

        var IBNC_driver = document.getElementById('IBNC_driver');
        IBNC_driver.volume = 1.0;
        IBNC_driver.muted = false;

        var CR_pedestrian = document.getElementById('CR_pedestrian');
        CR_pedestrian.volume = 1.0;
        CR_pedestrian.muted = false;

        var EN_pedestrian = document.getElementById('EN_pedestrian');
        EN_pedestrian.volume = 1.0;
        EN_pedestrian.muted = false;

        var IBN_pedestrian = document.getElementById('IBN_pedestrian');
        IBN_pedestrian.volume = 1.0;
        IBN_pedestrian.muted = false;

        var IBNC_pedestrian = document.getElementById('IBNC_pedestrian');
        IBNC_pedestrian.volume = 1.0;
        IBNC_pedestrian.muted = false;

        document.getElementById('simulate-button').addEventListener('click', function () {
            const form = document.getElementById('simulation-form');
            const formData = new FormData(form);
            const params = new URLSearchParams();
            for (let [key, value] of formData.entries()) {
                params.append(key, value);
            }

            const url = `DataServlet?${params.toString()}`;

            fetch(url, {
                    method: 'GET'
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {

                    console.log(data);
                    // Update traffic light status
                    document.getElementById('traffic-light-red').style.backgroundColor = data
                        .trafficLightStatus === 'RED' ? 'red' : 'grey';
                    document.getElementById('traffic-light-yellow').style.backgroundColor = data
                        .trafficLightStatus === 'YELLOW' ? 'yellow' : 'grey';
                    document.getElementById('traffic-light-green').style.backgroundColor = data
                        .trafficLightStatus === 'GREEN' ? 'green' : 'grey';

                    // Update notifications

                    if (data.collisionRisk) {

                        if (!document.getElementById('notification-none').classList.contains(
                                'd-none')) {
                            document.getElementById('notification-none').classList.add(
                                'd-none');
                        }

                        if (!document.getElementById('notification-safe').classList.contains(
                                'd-none')) {
                            document.getElementById('notification-safe').classList.add(
                                'd-none');
                        }

                        if (document.getElementById('notification-danger').classList.contains(
                                'd-none')) {
                            document.getElementById('notification-danger').classList.remove(
                                'd-none');
                        }

                        if (data.userNotification || data.vehicleNotification) {
                            if (data.userNotification && data.vehicleNotification) {
                                const userNotification = JSON.parse(data.userNotification);
                                const vehicleNotification = JSON.parse(data.vehicleNotification);
                                collisionRiskNotification(userNotification, vehicleNotification);
                            } else {
                                if (data.userNotification) {
                                    if (Object.keys(data.userNotification).length > 0) {
                                        const userNotification = JSON.parse(data.userNotification);
                                        collisionRiskNotificationPedestrian(userNotification);
                                    }
                                }
                                if (data.vehicleNotification) {
                                    if (Object.keys(data.vehicleNotification).length > 0) {
                                        const vehicleNotification = JSON.parse(data.vehicleNotification);
                                        collisionRiskNotificationDriver(vehicleNotification);

                                    }
                                }
                            }
                        } else {
                            noColisionRiskNotification();
                        }
                    } else {
                        if (!document.getElementById('notification-none').classList.contains(
                                'd-none')) {
                            document.getElementById('notification-none').classList.add(
                                'd-none');
                        }

                        if (!document.getElementById('notification-danger').classList.contains(
                                'd-none')) {
                            document.getElementById('notification-danger').classList.add(
                                'd-none');
                        }

                        if (document.getElementById('notification-safe').classList.contains(
                                'd-none')) {
                            document.getElementById('notification-safe').classList.remove(
                                'd-none');
                        }

                        noColisionRiskNotification();
                    }

                    // Update console output
                    document.getElementById('console-output').textContent = data.consoleOutput;

                });
        });

        function collisionRiskNotification(userNotification, vehicleNotification) {

            const smartphone = document.getElementById(
                'smartphone-notification');

            const vehicle = document.getElementById('vehicle-notification');
            if (smartphone.classList.contains('alert-info')) {
                smartphone.classList.remove('alert-info');
            }
            if (smartphone.classList.contains('alert-success')) {
                smartphone.classList.remove('alert-success');
            }
            smartphone.classList.add('alert-danger');

            smartphone.textContent = userNotification.message;

            if (vehicle.classList.contains('alert-info')) {
                vehicle.classList.remove('alert-info');
            }
            if (vehicle.classList.contains('alert-success')) {
                vehicle.classList.remove('alert-success');
            }
            vehicle.classList.add('alert-danger');

            vehicle.textContent = vehicleNotification.message;
        }

        function collisionRiskNotificationDriver(vehicleNotification) {

            const vehicle = document.getElementById('vehicle-notification');

            if (vehicle.classList.contains('alert-info')) {
                vehicle.classList.remove('alert-info');
            }
            if (vehicle.classList.contains('alert-success')) {
                vehicle.classList.remove('alert-success');
            }
            vehicle.classList.add('alert-danger');

            vehicle.textContent = vehicleNotification.message;

            switch (vehicleNotification.detail) {
                case 'CR':
                    document.getElementById('CR_driver').play();
                    break;
                case 'IBN':
                    document.getElementById('IBN_driver').play();
                    break;
                case 'EN':
                    document.getElementById('EN_driver').play();
                    break;
                case 'IBNC':
                    document.getElementById('IBNC_driver').play();
                    break;
                default:
                    console.log('Unknown condition');
                    break;
            }
        }

        function collisionRiskNotificationPedestrian(userNotification) {

            const smartphone = document.getElementById(
                'smartphone-notification');

            if (smartphone.classList.contains('alert-info')) {
                smartphone.classList.remove('alert-info');
            }
            if (smartphone.classList.contains('alert-success')) {
                smartphone.classList.remove('alert-success');
            }
            smartphone.classList.add('alert-danger');

            smartphone.textContent = userNotification.message;

            switch (userNotification.detail) {
                case 'CR':
                    document.getElementById('CR_pedestrian').play();
                    break;
                case 'IBN':
                    document.getElementById('IBN_pedestrian').play();
                    break;
                case 'EN':
                    document.getElementById('EN_pedestrian').play();
                    break;
                case 'IBNC':
                    document.getElementById('IBNC_pedestrian').play();
                    break;
                default:
                    console.log('Unknown condition');
                    break;
            }
        }

        function noColisionRiskNotification() {
            const smartphoneNotification = document.getElementById(
                'smartphone-notification');

            const vehicleNotification = document.getElementById('vehicle-notification');

            if (smartphoneNotification.classList.contains('alert-danger')) {
                smartphoneNotification.classList.remove('alert-danger');
            }
            if (smartphoneNotification.classList.contains('alert-success')) {
                smartphoneNotification.classList.remove('alert-success');
            }
            smartphoneNotification.classList.add('alert-info');

            smartphoneNotification.textContent = 'Nenhuma notificação.';

            if (vehicleNotification.classList.contains('alert-danger')) {
                vehicleNotification.classList.remove('alert-danger');
            }
            if (vehicleNotification.classList.contains('alert-success')) {
                vehicleNotification.classList.remove('alert-success');
            }
            vehicleNotification.classList.add('alert-info');

            vehicleNotification.textContent = 'Nenhuma notificação.';
        }
    </script>
</body>

</html>