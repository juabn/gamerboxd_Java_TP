package servidor;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class ServerHTTP {

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
		
		server.createContext("/", new manejarEntradas());
		server.start();
		System.out.println("Server corriendo");
		

				
	}
	
	
	
	public class recibirdatos {
		
		
		private String respuesta;

		public String getRespuesta() {
			return respuesta;
		}

		public void setRespuesta(String respuesta) {
			this.respuesta = respuesta;
		}
		
		
	}
	
	
	public static class manejarEntradas implements HttpHandler  {
		
		
		public void handle(HttpExchange exchange) throws IOException {
			
			
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
		    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
			
			
			
			System.out.println("Petición recibida!");

			InputStream is = exchange.getRequestBody();
			String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			
			System.out.println(body);
            String respuesta = "Si ves esto, es porque funciona!";
            
            
            exchange.sendResponseHeaders(200, respuesta.getBytes().length);
            
            
            OutputStream os = exchange.getResponseBody();
            os.write(respuesta.getBytes());
            String metodo = exchange.getRequestMethod();
            System.out.println(metodo);
            os.close();
		}
		
	}
	
	
	
	
	

}
