package servidor;

import data.Data_persona;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
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
	
	
	public static class persona {
		
		public String username;
		public String password;
		
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
	}
	
	
	public static class manejarEntradas implements HttpHandler  {
		
		
		public void handle(HttpExchange exchange) throws IOException {
			
			
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
		    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
			
			
		    if (exchange.getRequestMethod().equals("OPTIONS")) {

		        exchange.sendResponseHeaders(204, -1);

		        return;
		    }
			

			InputStream is = exchange.getRequestBody();
			String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			
			Gson gson = new Gson();
			persona per = gson.fromJson(body, persona.class);	
			System.out.println(per.getUsername());
			System.out.println(per.getPassword());
			
			Data_persona.insertar_persona(per.getUsername(),per.getPassword() );
		
			
            String respuesta = "Funciona";
            exchange.sendResponseHeaders(200, respuesta.getBytes().length);
            
            
            OutputStream os = exchange.getResponseBody();
            os.write(respuesta.getBytes());
            
            
            os.close();
		}
		
	}
	
	
	
	
	

}
