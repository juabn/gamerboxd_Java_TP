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

import entities.Persona;

public class AbmcUsuario {
	
	

	//metodo para controlar cors
	public static void controlCors(HttpExchange exchange) {
		
		exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
	    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
	}
	
	
	
	
	
	public static class verificartoken implements HttpHandler {
		
		public void handle(HttpExchange exchange) throws IOException {
			
			String respuesta = "aaa no seee";
			
			controlCors(exchange);
			
		    if (exchange.getRequestMethod().equals("OPTIONS")) {

		        exchange.sendResponseHeaders(204, -1);
		        exchange.close();

		        return;
		    }
		    
		    try {
		    	
			    InputStream is = exchange.getRequestBody();
			    String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			    is.close();
			    
			    Gson gson = new Gson();
				Persona per = gson.fromJson(body, Persona.class);
				Boolean respuesta_token = Data_persona.veriToken(per.getToken(), per.getMail());
				if (respuesta_token == true) {
					
					respuesta = "todo bem";
					exchange.sendResponseHeaders(200, respuesta.getBytes().length);
					
					
				}
				
				else {
					
					respuesta = "token vencido a ver";
			    	exchange.sendResponseHeaders(401, respuesta.getBytes().length);
					
				}
				
    	
		    	
		    }catch(Error e ) {
		    	
		    	respuesta = "todinho mal";
		    	exchange.sendResponseHeaders(401, respuesta.getBytes().length);
		    	
		    	
		    }
		    
		    OutputStream os = exchange.getResponseBody();
            os.write(respuesta.getBytes(StandardCharsets.UTF_8));
            os.close();
			
			
		}
		
		
	}
	
	
	
	public static class recuperarpersona implements HttpHandler {
		
		
		public void handle(HttpExchange exchange) throws IOException {
			
			 String respuesta = "";
			
			
			
			controlCors(exchange);
			
		    if (exchange.getRequestMethod().equals("OPTIONS")) {

		        exchange.sendResponseHeaders(204, -1);
		        exchange.close();

		        return;
		    }
		    
		    try {
		    InputStream is = exchange.getRequestBody();
		    String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			is.close();
		    
		    respuesta = "ok";
		    Gson gson = new Gson();
			Persona per = gson.fromJson(body, Persona.class);	
			String mail = per.getMail();
			Data_persona.enviar_token(mail);
			
			exchange.sendResponseHeaders(200, respuesta.getBytes().length);
		    }
		    catch(Error e){
		    	respuesta = "ok";
		    	exchange.sendResponseHeaders(401, respuesta.getBytes().length);
		    }
			
		    
		    OutputStream os = exchange.getResponseBody();
            os.write(respuesta.getBytes(StandardCharsets.UTF_8));
            os.close();
		}
	}
	
	
	
	
	
	public static class login implements HttpHandler  {
		
		
		public void handle(HttpExchange exchange) throws IOException {
			
		    
			controlCors(exchange);
			
			
		    if (exchange.getRequestMethod().equals("OPTIONS")) {

		        exchange.sendResponseHeaders(204, -1);
		        exchange.close();

		        return;
		    }
			
		    String respuesta = "Funciona";
			InputStream is = exchange.getRequestBody();
			String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			is.close();
			
			
			Gson gson = new Gson();
			Persona per = gson.fromJson(body, Persona.class);	
			System.out.println(per.getMail());
			System.out.println(per.getContrasena());
			
			
			Boolean resultado = Data_persona.buscar_persona(per.getMail(),per.getContrasena() );
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
            os.write(respuesta.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();
            exchange.close();
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
			System.out.println(body);
			
			Gson gson = new Gson();
			Persona per = gson.fromJson(body, Persona.class);	
			
			System.out.println(per.getNombre_usuario());
			System.out.println(per.getContrasena());
			System.out.println(per.getMail());
			System.out.println(per.getFoto_perfil());
			
			
			
			Data_persona.insertar_persona(per.getNombre_usuario(), per.getContrasena(), per.getMail(), "usuario", per.getFoto_perfil());
			
			
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
