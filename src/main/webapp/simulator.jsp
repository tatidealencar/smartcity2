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
        <h1 class="text-center mb-4">Simulador de Controle de Tráfico</h1>

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

            <button type="button" id="simulate-button" class="btn btn-primary">Simular</button>
        </form>

        <div class="notifications mt-3">
            <div id="notification-danger" class="alert alert-danger d-none">Atenção! Risco de colisão detectado!</div>
            <div id="notification-safe" class="alert alert-success d-none">Nenhum risco de colisão.</div>
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
        </div>

        <div class="mt-3">
            <h4>Console de Saída do Sistema</h4>
            <div class="console">
                <div id="console-output" class="console-output"></div>
            </div>
        </div>
    </div>
    <script>
        document.getElementById('simulate-button').addEventListener('click', function () {
            const form = document.getElementById('simulation-form');
            const formData = new FormData(form);
            const params = new URLSearchParams();
            for (let [key, value] of formData.entries()) {
                params.append(key, value);
            }

            const url = `DataServlet?${params.toString()}`;
            console.log(url);

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
                        document.getElementById('notification-danger').classList.remove('d-none');
                        document.getElementById('notification-safe').classList.add('d-none');
                        document.getElementById('smartphone-notification').classList.replace('alert-info',
                            'alert-danger');
                        document.getElementById('smartphone-notification').textContent =
                            'Atenção! Risco de colisão detectado!';
                        document.getElementById('vehicle-notification').classList.replace('alert-info',
                            'alert-danger');
                        document.getElementById('vehicle-notification').textContent =
                            'Atenção! Risco de colisão detectado!';
                    } else {
                        document.getElementById('notification-danger').classList.add('d-none');
                        document.getElementById('notification-safe').classList.remove('d-none');
                        document.getElementById('smartphone-notification').classList.replace('alert-danger',
                            'alert-success');
                        document.getElementById('smartphone-notification').textContent =
                            'Nenhum risco de colisão.';
                        document.getElementById('vehicle-notification').classList.replace('alert-danger',
                            'alert-success');
                        document.getElementById('vehicle-notification').textContent =
                            'Nenhum risco de colisão.';
                    }

                    // Update console output
                    document.getElementById('console-output').textContent = data.consoleOutput;

                });
        });
    </script>
</body>

</html>