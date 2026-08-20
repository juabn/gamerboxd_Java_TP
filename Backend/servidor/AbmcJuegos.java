package servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.SecretKey;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import entities.Compania;
import entities.Juego;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import data.Conexion;
import data.DataCompania;
import data.DataJuego;
import data.Cors;

public class AbmcJuegos {
	
	private static final SecretKey KEY = GeneracionWebToken.llaveJWT();
	
	

	
	
	
public static class juegoid implements HttpHandler{
	
	public void handle(HttpExchange exchange) throws IOException{
		
		Cors.controlCors(exchange);
		
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
			
			
			Cors.controlCors(exchange);
			
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
				        + "LEFT JOIN juego_compania ON juego_compania.idjuego = juego.idjuego \r\n"
				        + "LEFT JOIN compania ON juego_compania.id_comp = compania.idcompania AND LOWER(compania.estado) != ? \r\n"
				        + "WHERE juego.estado = 'activo' \r\n"
				        + "GROUP BY juego.idjuego;";
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
}

public static class existejuego implements HttpHandler{
	
	public void handle(HttpExchange exchange) throws IOException{
		
		String respuesta;
		
		boolean existejuego = false;
		Cors.controlCors(exchange);
		
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
    	    
		}catch(Exception e ) {
	    	
			respuesta = "Error token";
	    	exchange.sendResponseHeaders(402, respuesta.getBytes().length);
	    	
		}
		
	    try {
	    	
    	 	InputStream is = exchange.getRequestBody();
		    String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
		    is.close();
		    Gson gson = new Gson();
			Juego juego = gson.fromJson(body, Juego.class);
			
			existejuego = DataJuego.buscarjuego(juego.getTitulo());
					
			if(!existejuego) {
				
				respuesta = "No existe empresa";
		    	exchange.sendResponseHeaders(404, respuesta.getBytes().length);
				
			}
			
	    	respuesta = "todo bem";
	    	exchange.sendResponseHeaders(200, respuesta.getBytes().length);

    	
    }
    catch(Error e ) {
    	
    	respuesta = "Error en la bd";
    	exchange.sendResponseHeaders(401, respuesta.getBytes().length);
    	
    	
    }
    	
    	
    OutputStream os = exchange.getResponseBody();
    os.write(respuesta.getBytes(StandardCharsets.UTF_8));
    os.close();
	
    	
    }
	

}




	public static class devolverjuego implements HttpHandler{
	
	public void handle(HttpExchange exchange) throws IOException {
		
		
		Juego juego = new Juego();
		Juego juegoRespuesta = new Juego();
		
		String respuesta = "aaa no seee";
		

		Cors.controlCors(exchange);
		
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
    	    
		}catch(Exception e ) {
	    	
			respuesta = "Error token";
	    	exchange.sendResponseHeaders(402, respuesta.getBytes().length);
	    	
	    	
		}
	    
	    try {
	    	
	    	 	InputStream is = exchange.getRequestBody();
			    String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			    is.close();
			    Gson gson = new Gson();
			    juego = gson.fromJson(body, Juego.class);
			   
				
				juegoRespuesta = DataJuego.recuperarPorTitulo(juego.getTitulo());
				
				respuesta = gson.toJson(juegoRespuesta);
				
		    	exchange.sendResponseHeaders(200, respuesta.getBytes().length);

	    	
	    }
	    catch(Exception e ) {
	    	
	    	respuesta = "Error en la bd";
	    	exchange.sendResponseHeaders(401, respuesta.getBytes().length);
	    	
	    	
	    }
	    	
	    OutputStream os = exchange.getResponseBody();
        os.write(respuesta.getBytes(StandardCharsets.UTF_8));
        os.close();
		
	    	
	    }
	
	
	
	
}
	
	
	
	
	
	public static class actualizardatosjuego implements HttpHandler {
		
		
		public void handle(HttpExchange exchange) throws IOException {
			
			
			Boolean existe;
			Juego juego = new Juego();
			Juego comRespuesta = new Juego();
			
			String respuesta = "aaa no seee";
			
			
			
			Cors.controlCors(exchange);
			
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
	    	    
			}catch(Exception e ) {
		    	
				respuesta = "Error token";
		    	exchange.sendResponseHeaders(402, respuesta.getBytes().length);
		    	
			}
		    
		    try {
		    	
		    	 	InputStream is = exchange.getRequestBody();
				    String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
				    is.close();
				    Gson gson = new Gson();
				    juego = gson.fromJson(body, Juego.class);
				    
				   
				    	
				    existe = DataJuego.buscarjuego(juego.getTitulo());
				    
				    
				    if(existe) {
				    	
					respuesta = "empresa repetida";
						

						
			    	exchange.sendResponseHeaders(409, respuesta.getBytes().length);
				    }
				    else if (!existe) {
				    	
				    	try {
				    		
				    	    
				    	    DataJuego.actualizarJuego(
				    	    	Integer.parseInt(juego.getId_juego()),
				    	        juego.getTitulo(), 
				    	        juego.getEstado(), 
				    	        juego.getImagen(), 
				    	        juego.getDescripcion()
				    	    );
				    	    
				    	    respuesta = "Actualizado con exito";
				    	    byte[] bytesResp = respuesta.getBytes(StandardCharsets.UTF_8);
				    	    exchange.sendResponseHeaders(200, bytesResp.length);

				    	} catch(Exception e) {
				    	    System.out.println("Error al actualizar en la BD: " + e.getMessage());
				    	    
				    	    
				    	    respuesta = "Error en la bd";
				    	    byte[] bytesError = respuesta.getBytes(StandardCharsets.UTF_8);
				    	    exchange.sendResponseHeaders(500, bytesError.length);
				    	    
				    	} finally {
				    	    OutputStream os = exchange.getResponseBody();
				    	    os.write(respuesta.getBytes(StandardCharsets.UTF_8));
				    	    os.close();
				    	}
				    	
				    	
				    }


		    	
		    }
		    catch(Exception e ) {
		    	
		    	respuesta = "Error en la bd";
		    	exchange.sendResponseHeaders(401, respuesta.getBytes().length);
		    	
		    	
		    }
		    	
		    	
		    OutputStream os = exchange.getResponseBody();
            os.write(respuesta.getBytes(StandardCharsets.UTF_8));
            os.close();
			
		    	
		    }
		
		
		
		
		
		
	}


}
