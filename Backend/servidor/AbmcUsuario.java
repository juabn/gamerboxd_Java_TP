package servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import data.Data_persona;


public class AbmcUsuario {
	
	
	
	//Clase que utiliza gson
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
	
	
	//metodo para controlar cors
	public static void controlCors(HttpExchange exchange) {
		
		exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
	    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
	}
	
	
	public static class login implements HttpHandler  {
		
		
		public void handle(HttpExchange exchange) throws IOException {
			
			
			controlCors(exchange);
			
			
		    if (exchange.getRequestMethod().equals("OPTIONS")) {

		        exchange.sendResponseHeaders(204, -1);

		        return;
		    }
			
		    String respuesta = "Funciona";
			InputStream is = exchange.getRequestBody();
			String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			
			Gson gson = new Gson();
			persona per = gson.fromJson(body, persona.class);	
			System.out.println(per.getUsername());
			System.out.println(per.getPassword());
			
			
			Boolean resultado = Data_persona.buscar_persona(per.getUsername(),per.getPassword() );
			if (resultado) {
				System.out.println("Usuario existe y la contrasenia es correcta");
				respuesta = "Usuario existe";
				exchange.sendResponseHeaders(200, respuesta.getBytes().length);
				
			}
			else {
				
				respuesta = "Usuario no existe o credenciales incorrectas";
				exchange.sendResponseHeaders(401, respuesta.getBytes().length);
			}
			
            
            
            OutputStream os = exchange.getResponseBody();
            os.write(respuesta.getBytes());
            
            
            os.close();
		}
		
	}

}
