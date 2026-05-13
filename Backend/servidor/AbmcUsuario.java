package servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import data.Data_persona;



public class AbmcUsuario {
	
	
	
	//Clase que utiliza gson
	public static class personalogin {
		
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
	
	public static class personaregistro {
		
		public String username;
		public String password;
		public String mail;
		
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
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
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
			personalogin per = gson.fromJson(body, personalogin.class);	
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
	
	
	public static class registro implements HttpHandler{
		
		public void handle(HttpExchange exchange) throws IOException {
			
			
			controlCors(exchange);
			
			
			if (exchange.getRequestMethod().equals("OPTIONS")) {
		        exchange.sendResponseHeaders(204, -1);
		        return;
		    }
			
			String mensaje = "funcionando";
			
			
			try {
			InputStream is = exchange.getRequestBody();
			String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			
			Gson gson = new Gson();
			personaregistro per = gson.fromJson(body, personaregistro.class);	
			
			
			
			Data_persona.insertar_persona(per.getUsername(), per.getPassword(), per.getMail(), "usuario");
			
			System.out.println(per.getUsername());
			System.out.println(per.getPassword());
			System.out.println(per.getMail());
			
			
			exchange.sendResponseHeaders(200, mensaje.getBytes().length);
			OutputStream os = exchange.getResponseBody();
            os.write(mensaje.getBytes()); 
            os.close();
			
			}catch(SQLException e) {
				
				System.out.println(e.getErrorCode());
				

				if (e.getErrorCode() == 1062) {
					
					mensaje = "usuario duplicadoooo";
					exchange.sendResponseHeaders(409, mensaje.getBytes().length);
					OutputStream os = exchange.getResponseBody();
		            os.write(mensaje.getBytes()); 
		            os.close();
					
				}else {
					
					mensaje = "error al acceder a la bd";
					exchange.sendResponseHeaders(403, mensaje.getBytes().length);
					OutputStream os = exchange.getResponseBody();
		            os.write(mensaje.getBytes()); 
		            os.close();
					
					
				}
			}
			

			
			
			
		}
		
	}

}
