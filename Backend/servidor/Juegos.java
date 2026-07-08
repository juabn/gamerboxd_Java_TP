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
		
		exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
	    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
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
	
				String query = "SELECT juego.idjuego, juego.titulo, juego.imagen, juego.descripcion, "
			            + "GROUP_CONCAT(compania.nombre SEPARATOR ', ') AS todas_las_companias " 
			            + "FROM juego "
			            + "INNER JOIN juego_compania ON juego_compania.idjuego = juego.idjuego "
			            + "INNER JOIN compania ON juego_compania.id_comp = compania.idcompania "
			            + "GROUP BY juego.idjuego, juego.titulo, juego.imagen, juego.descripcion";
				PreparedStatement Resultado = conn.prepareStatement(query);
				ResultSet rs = Resultado.executeQuery();
				
				
				while (rs.next()) {
					
					Juego juego = new Juego();
					
					juego.setId_juego(rs.getString("idjuego"));
					juego.setTitulo(rs.getString("titulo"));
					juego.setImagen(rs.getString("imagen"));
					juego.setDescripcion(rs.getString("descripcion"));
					juego.setCompanias(rs.getString("todas_las_companias"));
					
					
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
