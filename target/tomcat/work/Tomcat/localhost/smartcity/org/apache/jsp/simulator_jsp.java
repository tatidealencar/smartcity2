/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2024-07-11 02:06:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class simulator_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"ISO-8859-1\">\r\n");
      out.write("    <title>SmartCity Simulator</title>\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n");
      out.write("        integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\r\n");
      out.write("    <link href=\"./style.css\" rel=\"stylesheet\">\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"\r\n");
      out.write("        integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\">\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"container mt-5\">\r\n");
      out.write("        <h1 class=\"text-center mb-4\">Simulador de Controle de Tráfego</h1>\r\n");
      out.write("\r\n");
      out.write("        <form id=\"simulation-form\">\r\n");
      out.write("            <div class=\"row mb-3\">\r\n");
      out.write("                <div class=\"col\">\r\n");
      out.write("                    <label for=\"traffic-light-lat\" class=\"form-label\">Latitude do Semáforo</label>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"traffic-light-lat\" name=\"trafficLightLat\"\r\n");
      out.write("                        placeholder=\"Ex.: -23.55052\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col\">\r\n");
      out.write("                    <label for=\"traffic-light-lng\" class=\"form-label\">Longitude do Semáforo</label>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"traffic-light-lng\" name=\"trafficLightLng\"\r\n");
      out.write("                        placeholder=\"Ex.: -46.633308\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col\">\r\n");
      out.write("                    <label for=\"traffic-light-status\" class=\"form-label\">Status Inicial do Semáforo</label>\r\n");
      out.write("                    <select class=\"form-control\" id=\"traffic-light-status\" name=\"trafficLightStatus\">\r\n");
      out.write("                        <option value=\"RED\">Vermelho</option>\r\n");
      out.write("                        <option value=\"YELLOW\">Amarelo</option>\r\n");
      out.write("                        <option value=\"GREEN\">Verde</option>\r\n");
      out.write("                    </select>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"row mb-3\">\r\n");
      out.write("                <div class=\"col\">\r\n");
      out.write("                    <label for=\"pedestrian-lat\" class=\"form-label\">Latitude do Pedestre</label>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"pedestrian-lat\" name=\"pedestrianLat\"\r\n");
      out.write("                        placeholder=\"Ex.: -23.55052\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col\">\r\n");
      out.write("                    <label for=\"pedestrian-lng\" class=\"form-label\">Longitude do Pedestre</label>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"pedestrian-lng\" name=\"pedestrianLng\"\r\n");
      out.write("                        placeholder=\"Ex.: -46.633308\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"row mb-3\">\r\n");
      out.write("                <div class=\"col\">\r\n");
      out.write("                    <label for=\"car-lat\" class=\"form-label\">Latitude do Carro</label>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"car-lat\" name=\"carLat\" placeholder=\"Ex.: -23.55052\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col\">\r\n");
      out.write("                    <label for=\"car-lng\" class=\"form-label\">Longitude do Carro</label>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"car-lng\" name=\"carLng\" placeholder=\"Ex.: -46.633308\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col\">\r\n");
      out.write("                    <label for=\"car-speed\" class=\"form-label\">Velocidade do Carro</label>\r\n");
      out.write("                    <input type=\"number\" class=\"form-control\" id=\"car-speed\" name=\"carSpeed\" placeholder=\"Ex.: 100\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"row mb-3\">\r\n");
      out.write("                <div class=\"col\">\r\n");
      out.write("                    <label for=\"test-options\" class=\"form-label\">Escolha uma opção de teste:</label>\r\n");
      out.write("                    <div id=\"test-options\">\r\n");
      out.write("                        <div class=\"form-check form-check-inline\">\r\n");
      out.write("                            <input class=\"form-check-input\" type=\"radio\" name=\"testOption\" id=\"car-test\" value=\"car\">\r\n");
      out.write("                            <label class=\"form-check-label\" for=\"car-test\">\r\n");
      out.write("                                Testar carro\r\n");
      out.write("                            </label>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-check form-check-inline\">\r\n");
      out.write("                            <input class=\"form-check-input\" type=\"radio\" name=\"testOption\" id=\"pedestrian-test\"\r\n");
      out.write("                                value=\"pedestrian\">\r\n");
      out.write("                            <label class=\"form-check-label\" for=\"pedestrian-test\">\r\n");
      out.write("                                Testar pedestre\r\n");
      out.write("                            </label>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-check form-check-inline\">\r\n");
      out.write("                            <input class=\"form-check-input\" type=\"radio\" name=\"testOption\" id=\"both-test\" value=\"both\">\r\n");
      out.write("                            <label class=\"form-check-label\" for=\"both-test\">\r\n");
      out.write("                                Testar ambos\r\n");
      out.write("                            </label>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"row mb-3\">\r\n");
      out.write("                <div class=\"col\">\r\n");
      out.write("                    <label for=\"risk-options\" class=\"form-label\">Escolha a condição de risco:</label>\r\n");
      out.write("                    <div id=\"risk-options\">\r\n");
      out.write("                        <div class=\"form-check\">\r\n");
      out.write("                            <input class=\"form-check-input\" type=\"radio\" name=\"riskOption\" id=\"cr-test\" value=\"CR\">\r\n");
      out.write("                            <label class=\"form-check-label\" for=\"cr-test\">\r\n");
      out.write("                                Risco de colisão (CR): Frenagem imediata necessária para evitar uma colisão potencial\r\n");
      out.write("                            </label>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-check\">\r\n");
      out.write("                            <input class=\"form-check-input\" type=\"radio\" name=\"riskOption\" id=\"ibn-test\" value=\"IBN\">\r\n");
      out.write("                            <label class=\"form-check-label\" for=\"ibn-test\">\r\n");
      out.write("                                Frenagem imediata necessária (IBN): Risco de colisão devido ao semáforo vermelho e\r\n");
      out.write("                                proximidade\r\n");
      out.write("                            </label>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-check\">\r\n");
      out.write("                            <input class=\"form-check-input\" type=\"radio\" name=\"riskOption\" id=\"en-test\" value=\"EN\">\r\n");
      out.write("                            <label class=\"form-check-label\" for=\"en-test\">\r\n");
      out.write("                                Emergência necessária (EN): Veículo e pedestre na mesma posição\r\n");
      out.write("                            </label>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-check\">\r\n");
      out.write("                            <input class=\"form-check-input\" type=\"radio\" name=\"riskOption\" id=\"ibnc-test\" value=\"IBNC\">\r\n");
      out.write("                            <label class=\"form-check-label\" for=\"ibnc-test\">\r\n");
      out.write("                                Frenagem imediata necessária (IBNC): Veículo muito próximo para parar com segurança\r\n");
      out.write("                            </label>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <button type=\"button\" id=\"simulate-button\" class=\"btn btn-primary\">Simular</button>\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"notifications mt-3\">\r\n");
      out.write("            <div id=\"notification-danger\" class=\"alert alert-danger d-none\">Atenção! Risco de colisão detectado!</div>\r\n");
      out.write("            <div id=\"notification-safe\" class=\"alert alert-success d-none\">Nenhum risco de colisão.</div>\r\n");
      out.write("            <div id=\"notification-none\" class=\"alert alert-info d-none\">Nenhuma notificação.</div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"simulation-container\">\r\n");
      out.write("            <div class=\"device smartphone\">\r\n");
      out.write("                <h4 class=\"text-center\">Smartphone</h4>\r\n");
      out.write("                <div id=\"smartphone-notification\" class=\"alert alert-info\">Nenhuma notificação</div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"device car-dashboard\">\r\n");
      out.write("                <div class=\"dashboard-title\">Painel do Carro</div>\r\n");
      out.write("                <div id=\"vehicle-notification\" class=\"notification alert-info\">Nenhuma notificação</div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"traffic-light\">\r\n");
      out.write("                <div class=\"light red\" id=\"traffic-light-red\"></div>\r\n");
      out.write("                <div class=\"light yellow\" id=\"traffic-light-yellow\"></div>\r\n");
      out.write("                <div class=\"light green\" id=\"traffic-light-green\"></div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <audio id=\"CR_driver\" src=\"audio/driver/CR.mp3\" type=\"audio/mp3\"></audio>\r\n");
      out.write("            <audio id=\"EN_driver\" src=\"audio/driver/EN.mp3\" type=\"audio/mp3\"></audio>\r\n");
      out.write("            <audio id=\"IBN_driver\" src=\"audio/driver/IBN.mp3\" type=\"audio/mp3\"></audio>\r\n");
      out.write("            <audio id=\"IBNC_driver\" src=\"audio/driver/IBNC.mp3\" type=\"audio/mp3\"></audio>\r\n");
      out.write("            <audio id=\"CR_pedestrian\" src=\"audio/pedestrian/CR.mp3\" type=\"audio/mp3\"></audio>\r\n");
      out.write("            <audio id=\"EN_pedestrian\" src=\"audio/pedestrian/EN.mp3\" type=\"audio/mp3\"></audio>\r\n");
      out.write("            <audio id=\"IBN_pedestrian\" src=\"audio/pedestrian/IBN.mp3\" type=\"audio/mp3\"></audio>\r\n");
      out.write("            <audio id=\"IBNC_pedestrian\" src=\"audio/pedestrian/IBNC.mp3\" type=\"audio/mp3\"></audio>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"mt-3\">\r\n");
      out.write("            <h4>Console de Saída do Sistema</h4>\r\n");
      out.write("            <div class=\"console\">\r\n");
      out.write("                <div id=\"console-output\" class=\"console-output\"></div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <script>\r\n");
      out.write("        var CR_driver = document.getElementById('CR_driver');\r\n");
      out.write("        CR_driver.volume = 1.0;\r\n");
      out.write("        CR_driver.muted = false;\r\n");
      out.write("\r\n");
      out.write("        var EN_driver = document.getElementById('EN_driver');\r\n");
      out.write("        EN_driver.volume = 1.0;\r\n");
      out.write("        EN_driver.muted = false;\r\n");
      out.write("\r\n");
      out.write("        var IBN_driver = document.getElementById('IBN_driver');\r\n");
      out.write("        IBN_driver.volume = 1.0;\r\n");
      out.write("        IBN_driver.muted = false;\r\n");
      out.write("\r\n");
      out.write("        var IBNC_driver = document.getElementById('IBNC_driver');\r\n");
      out.write("        IBNC_driver.volume = 1.0;\r\n");
      out.write("        IBNC_driver.muted = false;\r\n");
      out.write("\r\n");
      out.write("        var CR_pedestrian = document.getElementById('CR_pedestrian');\r\n");
      out.write("        CR_pedestrian.volume = 1.0;\r\n");
      out.write("        CR_pedestrian.muted = false;\r\n");
      out.write("\r\n");
      out.write("        var EN_pedestrian = document.getElementById('EN_pedestrian');\r\n");
      out.write("        EN_pedestrian.volume = 1.0;\r\n");
      out.write("        EN_pedestrian.muted = false;\r\n");
      out.write("\r\n");
      out.write("        var IBN_pedestrian = document.getElementById('IBN_pedestrian');\r\n");
      out.write("        IBN_pedestrian.volume = 1.0;\r\n");
      out.write("        IBN_pedestrian.muted = false;\r\n");
      out.write("\r\n");
      out.write("        var IBNC_pedestrian = document.getElementById('IBNC_pedestrian');\r\n");
      out.write("        IBNC_pedestrian.volume = 1.0;\r\n");
      out.write("        IBNC_pedestrian.muted = false;\r\n");
      out.write("\r\n");
      out.write("        document.getElementById('simulate-button').addEventListener('click', function () {\r\n");
      out.write("            const form = document.getElementById('simulation-form');\r\n");
      out.write("            const formData = new FormData(form);\r\n");
      out.write("            const params = new URLSearchParams();\r\n");
      out.write("            for (let [key, value] of formData.entries()) {\r\n");
      out.write("                params.append(key, value);\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("            const url = `DataServlet?${params.toString()}`;\r\n");
      out.write("\r\n");
      out.write("            fetch(url, {\r\n");
      out.write("                    method: 'GET'\r\n");
      out.write("                })\r\n");
      out.write("                .then(response => {\r\n");
      out.write("                    if (!response.ok) {\r\n");
      out.write("                        throw new Error('Network response was not ok');\r\n");
      out.write("                    }\r\n");
      out.write("                    return response.json();\r\n");
      out.write("                })\r\n");
      out.write("                .then(data => {\r\n");
      out.write("\r\n");
      out.write("                    console.log(data);\r\n");
      out.write("                    // Update traffic light status\r\n");
      out.write("                    document.getElementById('traffic-light-red').style.backgroundColor = data\r\n");
      out.write("                        .trafficLightStatus === 'RED' ? 'red' : 'grey';\r\n");
      out.write("                    document.getElementById('traffic-light-yellow').style.backgroundColor = data\r\n");
      out.write("                        .trafficLightStatus === 'YELLOW' ? 'yellow' : 'grey';\r\n");
      out.write("                    document.getElementById('traffic-light-green').style.backgroundColor = data\r\n");
      out.write("                        .trafficLightStatus === 'GREEN' ? 'green' : 'grey';\r\n");
      out.write("\r\n");
      out.write("                    // Update notifications\r\n");
      out.write("\r\n");
      out.write("                    if (data.collisionRisk) {\r\n");
      out.write("\r\n");
      out.write("                        if (!document.getElementById('notification-none').classList.contains(\r\n");
      out.write("                                'd-none')) {\r\n");
      out.write("                            document.getElementById('notification-none').classList.add(\r\n");
      out.write("                                'd-none');\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        if (!document.getElementById('notification-safe').classList.contains(\r\n");
      out.write("                                'd-none')) {\r\n");
      out.write("                            document.getElementById('notification-safe').classList.add(\r\n");
      out.write("                                'd-none');\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        if (document.getElementById('notification-danger').classList.contains(\r\n");
      out.write("                                'd-none')) {\r\n");
      out.write("                            document.getElementById('notification-danger').classList.remove(\r\n");
      out.write("                                'd-none');\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        if (data.userNotification || data.vehicleNotification) {\r\n");
      out.write("                            if (data.userNotification && data.vehicleNotification) {\r\n");
      out.write("                                const userNotification = JSON.parse(data.userNotification);\r\n");
      out.write("                                const vehicleNotification = JSON.parse(data.vehicleNotification);\r\n");
      out.write("                                collisionRiskNotification(userNotification, vehicleNotification);\r\n");
      out.write("                            } else {\r\n");
      out.write("                                if (data.userNotification) {\r\n");
      out.write("                                    if (Object.keys(data.userNotification).length > 0) {\r\n");
      out.write("                                        const userNotification = JSON.parse(data.userNotification);\r\n");
      out.write("                                        collisionRiskNotificationPedestrian(userNotification);\r\n");
      out.write("                                    }\r\n");
      out.write("                                }\r\n");
      out.write("                                if (data.vehicleNotification) {\r\n");
      out.write("                                    if (Object.keys(data.vehicleNotification).length > 0) {\r\n");
      out.write("                                        const vehicleNotification = JSON.parse(data.vehicleNotification);\r\n");
      out.write("                                        collisionRiskNotificationDriver(vehicleNotification);\r\n");
      out.write("\r\n");
      out.write("                                    }\r\n");
      out.write("                                }\r\n");
      out.write("                            }\r\n");
      out.write("                        } else {\r\n");
      out.write("                            noColisionRiskNotification();\r\n");
      out.write("                        }\r\n");
      out.write("                    } else {\r\n");
      out.write("                        if (!document.getElementById('notification-none').classList.contains(\r\n");
      out.write("                                'd-none')) {\r\n");
      out.write("                            document.getElementById('notification-none').classList.add(\r\n");
      out.write("                                'd-none');\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        if (!document.getElementById('notification-danger').classList.contains(\r\n");
      out.write("                                'd-none')) {\r\n");
      out.write("                            document.getElementById('notification-danger').classList.add(\r\n");
      out.write("                                'd-none');\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        if (document.getElementById('notification-safe').classList.contains(\r\n");
      out.write("                                'd-none')) {\r\n");
      out.write("                            document.getElementById('notification-safe').classList.remove(\r\n");
      out.write("                                'd-none');\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        noColisionRiskNotification();\r\n");
      out.write("                    }\r\n");
      out.write("\r\n");
      out.write("                    // Update console output\r\n");
      out.write("                    document.getElementById('console-output').textContent = data.consoleOutput;\r\n");
      out.write("\r\n");
      out.write("                });\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        function collisionRiskNotification(userNotification, vehicleNotification) {\r\n");
      out.write("\r\n");
      out.write("            const smartphone = document.getElementById(\r\n");
      out.write("                'smartphone-notification');\r\n");
      out.write("\r\n");
      out.write("            const vehicle = document.getElementById('vehicle-notification');\r\n");
      out.write("            if (smartphone.classList.contains('alert-info')) {\r\n");
      out.write("                smartphone.classList.remove('alert-info');\r\n");
      out.write("            }\r\n");
      out.write("            if (smartphone.classList.contains('alert-success')) {\r\n");
      out.write("                smartphone.classList.remove('alert-success');\r\n");
      out.write("            }\r\n");
      out.write("            smartphone.classList.add('alert-danger');\r\n");
      out.write("\r\n");
      out.write("            smartphone.textContent = userNotification.message;\r\n");
      out.write("\r\n");
      out.write("            if (vehicle.classList.contains('alert-info')) {\r\n");
      out.write("                vehicle.classList.remove('alert-info');\r\n");
      out.write("            }\r\n");
      out.write("            if (vehicle.classList.contains('alert-success')) {\r\n");
      out.write("                vehicle.classList.remove('alert-success');\r\n");
      out.write("            }\r\n");
      out.write("            vehicle.classList.add('alert-danger');\r\n");
      out.write("\r\n");
      out.write("            vehicle.textContent = vehicleNotification.message;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        function collisionRiskNotificationDriver(vehicleNotification) {\r\n");
      out.write("\r\n");
      out.write("            const vehicle = document.getElementById('vehicle-notification');\r\n");
      out.write("\r\n");
      out.write("            if (vehicle.classList.contains('alert-info')) {\r\n");
      out.write("                vehicle.classList.remove('alert-info');\r\n");
      out.write("            }\r\n");
      out.write("            if (vehicle.classList.contains('alert-success')) {\r\n");
      out.write("                vehicle.classList.remove('alert-success');\r\n");
      out.write("            }\r\n");
      out.write("            vehicle.classList.add('alert-danger');\r\n");
      out.write("\r\n");
      out.write("            vehicle.textContent = vehicleNotification.message;\r\n");
      out.write("\r\n");
      out.write("            switch (vehicleNotification.detail) {\r\n");
      out.write("                case 'CR':\r\n");
      out.write("                    document.getElementById('CR_driver').play();\r\n");
      out.write("                    break;\r\n");
      out.write("                case 'IBN':\r\n");
      out.write("                    document.getElementById('IBN_driver').play();\r\n");
      out.write("                    break;\r\n");
      out.write("                case 'EN':\r\n");
      out.write("                    document.getElementById('EN_driver').play();\r\n");
      out.write("                    break;\r\n");
      out.write("                case 'IBNC':\r\n");
      out.write("                    document.getElementById('IBNC_driver').play();\r\n");
      out.write("                    break;\r\n");
      out.write("                default:\r\n");
      out.write("                    console.log('Unknown condition');\r\n");
      out.write("                    break;\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        function collisionRiskNotificationPedestrian(userNotification) {\r\n");
      out.write("\r\n");
      out.write("            const smartphone = document.getElementById(\r\n");
      out.write("                'smartphone-notification');\r\n");
      out.write("\r\n");
      out.write("            if (smartphone.classList.contains('alert-info')) {\r\n");
      out.write("                smartphone.classList.remove('alert-info');\r\n");
      out.write("            }\r\n");
      out.write("            if (smartphone.classList.contains('alert-success')) {\r\n");
      out.write("                smartphone.classList.remove('alert-success');\r\n");
      out.write("            }\r\n");
      out.write("            smartphone.classList.add('alert-danger');\r\n");
      out.write("\r\n");
      out.write("            smartphone.textContent = userNotification.message;\r\n");
      out.write("\r\n");
      out.write("            switch (userNotification.detail) {\r\n");
      out.write("                case 'CR':\r\n");
      out.write("                    document.getElementById('CR_pedestrian').play();\r\n");
      out.write("                    break;\r\n");
      out.write("                case 'IBN':\r\n");
      out.write("                    document.getElementById('IBN_pedestrian').play();\r\n");
      out.write("                    break;\r\n");
      out.write("                case 'EN':\r\n");
      out.write("                    document.getElementById('EN_pedestrian').play();\r\n");
      out.write("                    break;\r\n");
      out.write("                case 'IBNC':\r\n");
      out.write("                    document.getElementById('IBNC_pedestrian').play();\r\n");
      out.write("                    break;\r\n");
      out.write("                default:\r\n");
      out.write("                    console.log('Unknown condition');\r\n");
      out.write("                    break;\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        function noColisionRiskNotification() {\r\n");
      out.write("            const smartphoneNotification = document.getElementById(\r\n");
      out.write("                'smartphone-notification');\r\n");
      out.write("\r\n");
      out.write("            const vehicleNotification = document.getElementById('vehicle-notification');\r\n");
      out.write("\r\n");
      out.write("            if (smartphoneNotification.classList.contains('alert-danger')) {\r\n");
      out.write("                smartphoneNotification.classList.remove('alert-danger');\r\n");
      out.write("            }\r\n");
      out.write("            if (smartphoneNotification.classList.contains('alert-success')) {\r\n");
      out.write("                smartphoneNotification.classList.remove('alert-success');\r\n");
      out.write("            }\r\n");
      out.write("            smartphoneNotification.classList.add('alert-info');\r\n");
      out.write("\r\n");
      out.write("            smartphoneNotification.textContent = 'Nenhuma notificação.';\r\n");
      out.write("\r\n");
      out.write("            if (vehicleNotification.classList.contains('alert-danger')) {\r\n");
      out.write("                vehicleNotification.classList.remove('alert-danger');\r\n");
      out.write("            }\r\n");
      out.write("            if (vehicleNotification.classList.contains('alert-success')) {\r\n");
      out.write("                vehicleNotification.classList.remove('alert-success');\r\n");
      out.write("            }\r\n");
      out.write("            vehicleNotification.classList.add('alert-info');\r\n");
      out.write("\r\n");
      out.write("            vehicleNotification.textContent = 'Nenhuma notificação.';\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
