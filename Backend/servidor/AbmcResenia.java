package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.SecretKey;

import data.Conexion;
import data.Data_persona;
import entities.Juego;
import entities.Persona;
import entities.Resenia;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AbmcResenia {
	private static final String SECRET_TEXT = "mi_clave_secreta_gamerboxd_tp_final_2026";
	private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_TEXT.getBytes(StandardCharsets.UTF_8));
	
	public static LinkedList<Resenia> recuperarTodos() {
		LinkedList<Resenia> resenias = new LinkedList<>();
		try {
			// crear una conexión
			Connection conn = Conexion.getInstancia().getConn();

			// ejecutar la query
            Statement stmt = conn.createStatement();
            String sql = "SELECT r.*, j.titulo AS nombre_juego, j.imagen AS foto_juego, p.nombre AS nombre_usuario " +
				     "FROM resenia r " +
				     "INNER JOIN juego j ON r.id_juego = j.idjuego " +
				     "INNER JOIN persona p ON r.mail_usuario = p.mail";
            ResultSet rs= stmt.executeQuery(sql);

            // mapear de resultset a objeto
            while(rs.next()) {
            	Resenia r=new Resenia();
            	
                r.setId_juego(rs.getInt("id_juego"));
                r.setTitulo(rs.getString("titulo"));
                r.setDescripcion(rs.getString("descripcion"));
                r.setFecha(rs.getString("fecha"));
                r.setHora(rs.getString("hora"));
                r.setPuntaje(rs.getFloat("puntaje"));
                r.setMail_usuario(rs.getString("mail_usuario"));
                
                Juego j = new Juego();
                j.setId_juego(rs.getString("id_juego"));
                j.setTitulo(rs.getString("nombre_juego")); 
                j.setImagen(rs.getString("foto_juego"));   
                r.setJuego(j);

                
                Persona p = new Persona();
                p.setMail(rs.getString("mail_usuario"));
                p.setNombre_usuario(rs.getString("nombre_usuario")); 
                r.setUsuario(p);
                resenias.add(r);

               

            }
            //cerrar recursos
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}

		    
		    
		    // mostrar info
		    System.out.println("Listado Completo");
		    System.out.println(resenias);
		    System.out.println();System.out.println();
		    
		    
		    
		    
		    

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return resenias;
		
	}
	
	public static class obtenerResenias implements HttpHandler {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
			exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
			exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
			if ("GET".equals(exchange.getRequestMethod())) {
				LinkedList<Resenia> listaResenias = recuperarTodos();
				
				Gson gson = new Gson();
				String jsonResponse = gson.toJson(listaResenias);
				
				byte[] bytesResponse = jsonResponse.getBytes("UTF-8");
				exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
				exchange.sendResponseHeaders(200, bytesResponse.length);
				
				OutputStream os = exchange.getResponseBody();
				os.write(bytesResponse);
				os.close();
				
			} else {
				exchange.sendResponseHeaders(405, -1);
			}
		}
	}
    
	
	public static void insertarNuevo(int idJuego, String mailUsuario, String titulo, String descripcion, float puntaje) {
		Resenia resenia= new Resenia();
		
		 resenia.setId_juego(idJuego);
		 resenia.setMail_usuario(mailUsuario);
		 resenia.setTitulo(titulo);
		 resenia.setDescripcion(descripcion);
		 resenia.setPuntaje(puntaje);
		
		try {
			Connection conn = Conexion.getInstancia().getConn();
			// definir la query
            PreparedStatement pstmt = conn.prepareStatement(
            		"insert into resenia(id_juego,fecha,hora,titulo,descripcion,puntaje,mail_usuario) values (?,?,?,?,?,?,?)"
            		,PreparedStatement.RETURN_GENERATED_KEYS
            		);
            
            
            LocalDate fecha = LocalDate.now();
            LocalTime hora = LocalTime.now();

            pstmt.setInt(1, idJuego);
            pstmt.setString(2, fecha.toString());
            pstmt.setString(3, hora.toString());
            pstmt.setString(4, titulo);
            pstmt.setString(5, descripcion);
            pstmt.setString(6, String.valueOf(puntaje));
            pstmt.setString(7, mailUsuario);

            pstmt.executeUpdate();

            if (pstmt != null) { pstmt.close(); }

            System.out.println("Nueva Resenia");
            System.out.println(resenia);
            System.out.println();
            System.out.println();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
	}
	
	public static class nuevaResenia implements HttpHandler{
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
	        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
	        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
	        
	        if ("OPTIONS".equals(exchange.getRequestMethod())) {
	            exchange.sendResponseHeaders(204, -1);
	            return;
	        }
	        
	        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
	    	
	    	String token = authHeader.substring(7);
	    	
    	    
    	    Claims claims = Jwts.parser()
    	    		.verifyWith(KEY) 
    	            .build()
    	            .parseSignedClaims(token)
    	            .getPayload();

    	    
    	    String mail = claims.getSubject();
    	    System.out.println(mail);
    	    Gson gson = new Gson();
    	    InputStream is = exchange.getRequestBody();
    	    String jsonBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);
	        Resenia nuevaResenia = gson.fromJson(jsonBody, Resenia.class);
	        nuevaResenia.setMail_usuario(mail);
	        
	        if (existeResenia(nuevaResenia.getId_juego(), mail)) {
	            
	            String error = "ya escribiste una reseña para este juego.";
	            exchange.sendResponseHeaders(400, error.getBytes().length);
	            OutputStream os = exchange.getResponseBody();
	            os.write(error.getBytes());
	            os.close();
	        } else {
	            
	            insertarNuevo(nuevaResenia.getId_juego(),nuevaResenia.getMail_usuario(),nuevaResenia.getTitulo(),nuevaResenia.getDescripcion(),nuevaResenia.getPuntaje());
	            String exito = "reseña guardada correctamente";
	            exchange.sendResponseHeaders(200, exito.getBytes().length);
	            OutputStream os = exchange.getResponseBody();
	            os.write(exito.getBytes());
	            os.close();
	        }
		}
	}
	
	public static boolean existeResenia(int idJuego, String mailUsuario) {
		boolean existe = false;

		
		try {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = Conexion.getInstancia().getConn();
			String sql = "SELECT COUNT(*) AS total FROM resenia WHERE id_juego = ? AND mail_usuario = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idJuego);
			stmt.setString(2, mailUsuario);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				if(rs.getInt("total")>0) {
					existe=true;
				}
			} 
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return existe;
	}

	
	public static class obtenerReseniasPorJuego implements HttpHandler {
	    @Override
	    public void handle(HttpExchange exchange) throws IOException {
	        
	        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
	        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
	        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
	        
	        
	        if ("OPTIONS".equals(exchange.getRequestMethod())) {
	            exchange.sendResponseHeaders(204, -1);
	            return;
	        }

	        if ("GET".equals(exchange.getRequestMethod())) {
	            String query = exchange.getRequestURI().getQuery();
	            int idJuego = -1;

	            if (query.contains("id=")) {
	                try {
	                    String[] parametros = query.split("&");
	                    for (String param : parametros) {
	                        if (param.startsWith("id=")) {
	                            idJuego = Integer.parseInt(param.split("=")[1]);
	                            break;
	                        }
	                    }
	                } catch (NumberFormatException e) {
	                    System.out.println("Error: El ID pasado no es un número válido.");
	                }
	            }

	            if (idJuego != -1) {
	                
	                List<Resenia> listaResenias = recuperarPorIdJuego(idJuego);
	                
	                Gson gson = new Gson();
	                String jsonResponse = gson.toJson(listaResenias);
	                
	                byte[] bytesResponse = jsonResponse.getBytes("UTF-8");
	                exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
	                exchange.sendResponseHeaders(200, bytesResponse.length);
	                
	                OutputStream os = exchange.getResponseBody();
	                os.write(bytesResponse);
	                os.close();
	            } else {
	                String error = "Falta el parametro 'id' en la URL";
	                byte[] bytesError = error.getBytes("UTF-8");
	                exchange.sendResponseHeaders(400, bytesError.length);
	                OutputStream os = exchange.getResponseBody();
	                os.write(bytesError);
	                os.close();
	            }
	            
	        } else {
	            
	            exchange.sendResponseHeaders(405, -1);
	        }
	    }
	}
	
	public static List<Resenia> recuperarPorIdJuego(int id) {

		LinkedList<Resenia> lista = new LinkedList<>();



		try {

			// crear una conexión
	
			Connection conn = Conexion.getInstancia().getConn();
	
	
	
			// definir la query
	
			PreparedStatement stmt = conn.prepareStatement("select * from resenia where id_juego=?");
	
	
	
			// setear el/los parámetros
	
			stmt.setInt(1, id);
	
	
	
			// ejecutar query y obtener resultados
	
			ResultSet rs = stmt.executeQuery();
	
	
	
			// mapear cada fila del resultset a un objeto y agregarlo a la lista
	
			while (rs.next()) {
	
				Resenia r = new Resenia();
				
				Persona p = new Persona();
				
				
		
				r.setId_juego(rs.getInt("id_juego"));
		
				r.setFecha(rs.getString("fecha"));
		
				r.setHora(rs.getString("hora"));
		
				r.setTitulo(rs.getString("titulo"));
		
				r.setDescripcion(rs.getString("descripcion"));
		
				r.setPuntaje(rs.getFloat("puntaje"));
		
				r.setMail_usuario(rs.getString("mail_usuario"));
				
				p = Data_persona.buscar_solo_persona_pormail(r.getMail_usuario());
				
				r.setUsuario(p);
		
				lista.add(r);
	
			}
	
		
		
				// cerrar recursos
		
				if (rs != null) { rs.close(); }
		
				if (stmt != null) { stmt.close(); }
		
				
		
		
		
				// mostrar objetos
		
				System.out.println("Buscar por id juego");
		
				System.out.println();
		
				System.out.println();
	
	
	
			} catch (SQLException ex) {
	
				// Manejo de errores
		
				System.out.println("SQLException: " + ex.getMessage());
		
				System.out.println("SQLState: " + ex.getSQLState());
		
				System.out.println("VendorError: " + ex.getErrorCode());
		
			}
	
			return lista;

		}
	
	public static List<Resenia> recuperarPorMailUsuario(String mail_usuario) {
		LinkedList<Resenia> lista = new LinkedList<>();

		try {
			// crear una conexión
			Connection conn = Conexion.getInstancia().getConn();

			// definir la query
			PreparedStatement stmt = conn.prepareStatement("select * from resenia where mail_usuario=?");

			// setear el/los parámetros
			stmt.setString(1, mail_usuario);

			// ejecutar query y obtener resultados
			ResultSet rs = stmt.executeQuery();

			// mapear cada fila del resultset a un objeto y agregarlo a la lista
			while (rs.next()) {
				Resenia r = new Resenia();
				r.setId_juego(rs.getInt("id_juego"));
				r.setFecha(rs.getString("fecha"));
				r.setHora(rs.getString("hora"));
				r.setTitulo(rs.getString("titulo"));
				r.setDescripcion(rs.getString("descripcion"));
				r.setPuntaje(rs.getFloat("puntaje"));
				r.setMail_usuario(rs.getString("mail_usuario"));
				lista.add(r);
			}

			// cerrar recursos
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
			conn.close();

			// mostrar objetos
			System.out.println("Buscar por mail usuario");
			System.out.println();
			System.out.println();

		} catch (SQLException ex) {
			// Manejo de errores
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return lista;
	}
}
