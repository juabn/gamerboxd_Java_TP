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

import data.Conexion;
import entities.Compania;

public class Empresas {
	
public static void controlCors(HttpExchange exchange) {
		
		exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
	    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
	}
	

	
public static class listaempresas implements HttpHandler {
		
		public void handle(HttpExchange exchange) throws IOException {
			
			controlCors(exchange);
			
		    if (exchange.getRequestMethod().equals("OPTIONS")) {

		        exchange.sendResponseHeaders(204, -1);
		        exchange.close();

		        return;
		    }
		    
		    ArrayList<Compania> listadeempresas = new ArrayList<>();
		    
			try {
				
				Connection conn = Conexion.getInstancia().getConn();
	
				String query = "select * from compania where estado <> ?";
				PreparedStatement Resultado = conn.prepareStatement(query);
				Resultado.setString(1, "inactivo");
				ResultSet rs = Resultado.executeQuery();
				
				
				while (rs.next()) {
					
					Compania compania = new Compania();
					
					compania.setId(rs.getInt("idcompania"));
					compania.setNombre(rs.getString("nombre"));
				
					
					
					listadeempresas.add(compania);
					
					
				}
				
				System.out.println(listadeempresas.get(1).getNombre());
		
			}
			catch(SQLException ex){
				
				
				System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
			
			Gson gson = new Gson();
		    String jsonRespuesta = gson.toJson(listadeempresas);
		    
		    
		    byte[] bytesRespuesta = jsonRespuesta.getBytes("UTF-8");
		    exchange.sendResponseHeaders(200, bytesRespuesta.length);
		    
		    
		    OutputStream os = exchange.getResponseBody();
		    os.write(bytesRespuesta);
		    os.close();
			
		    
		    
		}
		}

}
