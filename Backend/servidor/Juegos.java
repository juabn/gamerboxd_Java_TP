package servidor;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entities.Juego;

import data.Conexion;

public class Juegos {
	
	
	public static void controlCors(HttpExchange exchange) {
		/*
		exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
	    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
	    */
		exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
	    exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "*");
	}
	
	
	
public static class juegoid implements HttpHandler{
	
	public void handle(HttpExchange exchange) throws IOException{
		
		controlCors(exchange);
		
		if (exchange.getRequestMethod().equals("OPTIONS")) {

	        exchange.sendResponseHeaders(204, -1);
	        exchange.close();

	        return;
	    }
		
		Juego juego = null;
		try {
			String path = exchange.getRequestURI().getPath();
			String[] partes = path.split("/");
			String idJuego = partes[partes.length - 1];
			Connection conn = Conexion.getInstancia().getConn();
			
			
			String query = 
				    "SELECT juego.idjuego, juego.titulo, juego.imagen, juego.descripcion, " +
				    "GROUP_CONCAT(compania.nombre SEPARATOR ', ') AS todas_las_companias " +
				    "FROM juego " +
				    "LEFT JOIN juego_compania ON juego.idjuego = juego_compania.idjuego " +
				    "LEFT JOIN compania ON juego_compania.id_comp = compania.idcompania " +
				    "WHERE juego.idjuego = ? " +
				    "GROUP BY juego.idjuego, juego.titulo, juego.imagen, juego.descripcion";
			PreparedStatement resultado = conn.prepareStatement(query);
			resultado.setString(1, idJuego);
			ResultSet rs = resultado.executeQuery();
			
			if(rs.next()){
				juego = new Juego();
				
				juego.setId_juego(rs.getString("idjuego"));
				juego.setTitulo(rs.getString("titulo"));
				juego.setImagen(rs.getString("imagen"));
				juego.setDescripcion(rs.getString("descripcion"));
				juego.setCompanias(rs.getString("todas_las_companias"));
			}
			rs.close();
			resultado.close();
			if (juego != null) {
				Gson gson = new Gson();
			    String jsonRespuesta = gson.toJson(juego);
			    
			    byte[] bytesRespuesta = jsonRespuesta.getBytes("UTF-8");
			    exchange.sendResponseHeaders(200, bytesRespuesta.length);
			    
			    OutputStream os = exchange.getResponseBody();
			    os.write(bytesRespuesta);
			    os.close();
			} else {
				
				exchange.sendResponseHeaders(404, -1);
			    exchange.close();
			}
			
			
		}
		catch(SQLException ex){
			
			
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		String errorMsg = "Error en la base de datos";
	    exchange.sendResponseHeaders(500, errorMsg.getBytes().length);
	    OutputStream os = exchange.getResponseBody();
	    os.write(errorMsg.getBytes());
	    os.close();
	}
}
	

	
	
	
public static class listajuegos implements HttpHandler {
		
		public void handle(HttpExchange exchange) throws IOException {
			
			
			controlCors(exchange);
			
		    if (exchange.getRequestMethod().equals("OPTIONS")) {

		        exchange.sendResponseHeaders(204, -1);
		        exchange.close();

		        return;
		    }
			
			ArrayList<Juego> listadejuegos = new ArrayList<>();
			
		
			
			try {
				
				Connection conn = Conexion.getInstancia().getConn();
	
				String query = "SELECT juego.idjuego, juego.titulo, juego.imagen, juego.descripcion, \r\n"
						+ "       GROUP_CONCAT(compania.nombre SEPARATOR ', ') AS todas_las_companias \r\n"
						+ "FROM juego \r\n"
						+ "INNER JOIN juego_compania ON juego_compania.idjuego = juego.idjuego \r\n"
						+ "INNER JOIN compania ON juego_compania.id_comp = compania.idcompania \r\n"
						+ "WHERE LOWER(compania.estado) != ? \r\n"
						+ "GROUP BY juego.idjuego, juego.titulo, juego.imagen, juego.descripcion;";
				PreparedStatement Resultado = conn.prepareStatement(query);
				Resultado.setString(1, "inactivo");
				ResultSet rs = Resultado.executeQuery();
				
				
				while (rs.next()) {
					
					Juego juego = new Juego();
					
					juego.setId_juego(rs.getString("idjuego"));
					juego.setTitulo(rs.getString("titulo"));
					juego.setImagen(rs.getString("imagen"));
					juego.setDescripcion(rs.getString("descripcion"));
					juego.setCompanias(rs.getString("todas_las_companias"));
					// agregar precio y genero y puntaje promedio
					
					listadejuegos.add(juego);
					
					
				}
				
				
		
			}
			
			
			catch(SQLException ex){
				
				
				System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
			
			Gson gson = new Gson();
		    String jsonRespuesta = gson.toJson(listadejuegos);
		    
		    
		    byte[] bytesRespuesta = jsonRespuesta.getBytes("UTF-8");
		    exchange.sendResponseHeaders(200, bytesRespuesta.length);
		    
		    
		    OutputStream os = exchange.getResponseBody();
		    os.write(bytesRespuesta);
		    os.close();
			
			
		}
}}
