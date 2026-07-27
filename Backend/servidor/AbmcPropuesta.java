package servidor;

import java.io.IOException;


import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import javax.crypto.SecretKey;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import entities.Compania;
import entities.Persona;
import data.DataPropuesta;
import data.DataJuego;
import entities.Propuesta;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import entities.Propuesta;
public class AbmcPropuesta {
	
	//Clave JWT
	private static final String SECRET_TEXT = "mi_clave_secreta_gamerboxd_tp_final_2026";
	private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_TEXT.getBytes(StandardCharsets.UTF_8));

	
	//metodo para controlar cors
			public static void controlCors(HttpExchange exchange) {
				
				exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
			    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
			    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
			}
			
			
			
			
			
	public static class actualizarpropuesta implements HttpHandler {
		
		boolean resultado;
		boolean busquedajuego;
		String respuesta = "error";
		
		public void handle(HttpExchange exchange) throws IOException {
			
			controlCors(exchange);
			
		    if (exchange.getRequestMethod().equals("OPTIONS")) {

		        exchange.sendResponseHeaders(204, -1);
		        exchange.close();

		        return;
		    }
		    
		    

		    try {
		    	
		    	String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
		    	
		    	String token = authHeader.substring(7);
		    	
	    	    
	    	    Claims claims = Jwts.parser()
	    	    		.verifyWith(KEY) 
	    	            .build()
	    	            .parseSignedClaims(token)
	    	            .getPayload();
	    	    
	    	    
	    	    InputStream is = exchange.getRequestBody();
	    	    String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
	    	    is.close();
	    	    Gson gson = new Gson();
	    	    Propuesta pro = gson.fromJson(body, Propuesta.class);
	    	    
	    	    busquedajuego = DataJuego.buscarjuego(pro.getNombreJuego());
	    	    
	    	    System.out.println(pro.getFoto());
	    	    
    	    	
	    	    resultado = DataPropuesta.actualizarestado(pro);
	    	    
	    	    
	    	    
	    	    if(resultado) {		    		
		    		respuesta = "Bien";
		    		exchange.sendResponseHeaders(200, respuesta.getBytes().length);
		    		
		    	}	
	    	    
	    	    
		    }catch(Error e) {		    		
		    		respuesta = "Error token";	
		    	exchange.sendResponseHeaders(403, respuesta.getBytes().length);
		    	return;
		    	
		    }
		
		
	}
	}
	
	public static class listarPropuestas implements HttpHandler{
		String respuesta;
		LinkedList<Propuesta> propuestas = new LinkedList<>();
		
		
		
		public void handle(HttpExchange exchange) throws IOException {
			
			controlCors(exchange);
			
		    if (exchange.getRequestMethod().equals("OPTIONS")) {

		        exchange.sendResponseHeaders(204, -1);
		        exchange.close();

		        return;
		    }
		    
		    

		    try {
		    	
		    	String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
		    	
		    	String token = authHeader.substring(7);
		    	
	    	    
	    	    Claims claims = Jwts.parser()
	    	    		.verifyWith(KEY) 
	    	            .build()
	    	            .parseSignedClaims(token)
	    	            .getPayload();
    
		    	
		    	
		    }catch(Error e) {
		    	
		    	respuesta = "Error token";
		    	
		    	exchange.sendResponseHeaders(403, respuesta.getBytes().length);
		    	return;
		    	
		    }
		    
		    
		    try {
		    	
		    	Gson gson = new Gson();
		    	
		    	propuestas = DataPropuesta.listarpropuestas();
		    	
		    	respuesta = gson.toJson(propuestas);
		    	
		    	exchange.sendResponseHeaders(200, respuesta.getBytes().length);
		    	
				
   	
		    }catch(Error e) {
		    	
		    	respuesta = "Error";
		    	exchange.sendResponseHeaders(400, respuesta.getBytes().length);
		    	return;
		    	
		    	
		    }
				

		   
		   OutputStream os = exchange.getResponseBody();
	       os.write(respuesta.getBytes(StandardCharsets.UTF_8));
	       os.close();
		    
		    
				
			
			
			
			
		}
	}
			
	public static class crearPropuesta implements HttpHandler {
		
		public void handle(HttpExchange exchange) throws IOException {
			
			String respuesta;
			Boolean exito;
			String mail;
			
		controlCors(exchange);
			
	    if (exchange.getRequestMethod().equals("OPTIONS")) {

	        exchange.sendResponseHeaders(204, -1);
	        exchange.close();

	        return;
	    }
	    
	    
	    
	    try {
	    	
	    	String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
	    	
	    	String token = authHeader.substring(7);
	    	
    	    
    	    Claims claims = Jwts.parser()
    	    		.verifyWith(KEY) 
    	            .build()
    	            .parseSignedClaims(token)
    	            .getPayload();
    	    
	    	
	    	
    	    mail = claims.getSubject();
	    	
	    	
	    }catch(Error e) {
	    	
	    	respuesta = "Error token";
	    	exchange.sendResponseHeaders(403, respuesta.getBytes().length);
	    	return;
	    	
	    }
	    
	    
	    try {
	    	
	    	InputStream is = exchange.getRequestBody();
		    String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
		    is.close();
		    Gson gson = new Gson();
			Propuesta pro = gson.fromJson(body, Propuesta.class);
			
			
			
			exito =  DataJuego.buscarjuego(pro.getNombreJuego());
			
			System.out.println(exito);
			
			if(exito) {
				
				respuesta = "El juego ya existe";
		    	exchange.sendResponseHeaders(402, respuesta.getBytes().length);
		    	return;
				
			}
			else {
				
				
				exito = DataPropuesta.insertarpropuesta(pro.getNombreJuego(), pro.getDescripcionjuego(),mail, pro.getCompaniasJuego(), pro.getFoto());
				if(exito) {
					respuesta = "Propuesta cargada con exito";
			    	exchange.sendResponseHeaders(200, respuesta.getBytes().length);
			    	
					
				}else {
					
					respuesta = "Hubo un problema en la insercion";
			    	exchange.sendResponseHeaders(404, respuesta.getBytes().length);
			    	return;
					
				}
			}
	    	
	    	
	    	
	    }catch(Error e) {
	    	
	    	respuesta = "Error";
	    	exchange.sendResponseHeaders(400, respuesta.getBytes().length);
	    	return;
	    	
	    	
	    }
			

	   
	    OutputStream os = exchange.getResponseBody();
       os.write(respuesta.getBytes(StandardCharsets.UTF_8));
       os.close();
	    
	    
			
		}
		
	}
}
