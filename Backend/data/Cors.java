package data;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;

public class Cors {

    public static void controlCors(HttpExchange exchange) {
        try {
            // Permitir el origen exacto de Vercel y métodos necesarios
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "https://gamerboxdd.vercel.app");
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

            // Si es la petición preflight (OPTIONS), responde y detiene la ejecución
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*

package data;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;

public class Cors {

    public static void controlCors(HttpExchange exchange) {
        try {
            //Se permiten peticiones de cualquier origen
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type, Authorization");

            // Manejar la petición de tipo preflight (OPTIONS)
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

*/