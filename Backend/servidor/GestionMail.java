package servidor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GestionMail {

    private static final String SERVICE_ID = "service_ugxx7yx";
    private static final String TEMPLATE_ID = "template_g8f4utp";
    private static final String PUBLIC_KEY = "0a4aCAH1hwG6hPV34";
    private static final String PRIVATE_KEY = "H4lygm8O4aYAIhHxQMFQN"; 

    public static void enviarmail(String destino, String asunto, String tokenOMensaje) {
        System.out.println("--> Enviando mail vía EmailJS HTTP a: " + destino);

        try {
            String jsonBody = "{"
                    + "\"service_id\": \"" + SERVICE_ID + "\","
                    + "\"template_id\": \"" + TEMPLATE_ID + "\","
                    + "\"user_id\": \"" + PUBLIC_KEY + "\","
                    + "\"accessToken\": \"" + PRIVATE_KEY + "\","
                    + "\"template_params\": {"
                    + "\"to_email\": \"" + destino + "\","
                    + "\"subject\": \"" + asunto + "\","
                    + "\"token\": \"" + tokenOMensaje + "\""
                    + "}"
                    + "}";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.emailjs.com/api/v1.0/email/send"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status EmailJS: " + response.statusCode());
            System.out.println("Respuesta: " + response.body());

            if (response.statusCode() == 200) {
                System.out.println("--> EMAIL ENVIADO EXITOSAMENTE A: " + destino);
            } else {
                System.err.println("--> ERROR EMAILJS: " + response.body());
            }

        } catch (Exception e) {
            System.err.println("Error en peticion HTTP a EmailJS: " + e.getMessage());
            e.printStackTrace();
        }
    }
}