package servidor;
import entities.Compania;





import entities.Persona;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.crypto.SecretKey;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import servidor.GeneracionWebToken;

import data.Conexion;
import data.DataCompania;
import data.Data_persona;
import data.Cors;

public class AbmcCompania {
	

	private static final SecretKey KEY = GeneracionWebToken.llaveJWT();
	
	
		
	
		
		
	public static class actualizardatosdeempresa implements HttpHandler {
		
		
		public void handle(HttpExchange exchange) throws IOException {
			
			
			Boolean existe;
			Compania com = new Compania();
			Compania comRespuesta = new Compania();
			
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
				    com = gson.fromJson(body, Compania.class);
				    
				   
				    	
				    existe = DataCompania.validarempresaexistentenorepetida(com.getNombre(), com.getId());
				    
				    
				    if(existe) {
				    	
					respuesta = "empresa repetida";
						
					respuesta = gson.toJson(comRespuesta);
						
			    	exchange.sendResponseHeaders(409, respuesta.getBytes().length);
				    }
				    else if (!existe) {
				    	
				    	try {
				    	
				    	System.out.println(com.getId());
				    	
				    	respuesta = DataCompania.actualizarcompania(com.getNombre(), com.getEstado(), com.getId());
						
						respuesta = gson.toJson(comRespuesta);
							
				    	exchange.sendResponseHeaders(200, respuesta.getBytes().length);
				    	}catch(Exception e){
				    		
				    		respuesta = "mal";
							
							respuesta = gson.toJson(comRespuesta);
								
					    	exchange.sendResponseHeaders(401, respuesta.getBytes().length);
				    		
				    		
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
		
		
		
	public static class devolverempresa implements HttpHandler{
		
		public void handle(HttpExchange exchange) throws IOException {
			
			Compania com = new Compania();
			Compania comRespuesta = new Compania();
			
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
				    com = gson.fromJson(body, Compania.class);
					
					comRespuesta = DataCompania.recuperarPorMail(com.getNombre());
					
					respuesta = gson.toJson(comRespuesta);
					
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
		
		
		
	public static class existeempresa implements HttpHandler {
		
		public void handle(HttpExchange exchange) throws IOException {
			
			String respuesta = "aaa no seee";
			
			Boolean existeempresa;
			
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
					Compania com = gson.fromJson(body, Compania.class);
					
					existeempresa = DataCompania.validarempresaexistente(com.getNombre());
					
					if(!existeempresa) {
						
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
	
	
	
	
	
	
	
	public static class bajalogicacompania implements HttpHandler  {
		
		public void handle(HttpExchange exchange) throws IOException {
			
			String respuesta = "aaa no seee";
			
			Cors.controlCors(exchange);
			
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
					Compania com = gson.fromJson(body, Compania.class);
					
					DataCompania.dardebaja(com.getId());
					
			    	respuesta = "todo bem";
			    	exchange.sendResponseHeaders(200, respuesta.getBytes().length);

		    	
		    }
		    catch(Error e ) {
		    	
		    	respuesta = "Error";
		    	exchange.sendResponseHeaders(401, respuesta.getBytes().length);
		    	
		    	
		    }
		    	
		    	
		    OutputStream os = exchange.getResponseBody();
            os.write(respuesta.getBytes(StandardCharsets.UTF_8));
            os.close();
			
		    	
		    }
	}
	
	public static class actualizarnombrecompania implements HttpHandler {
		
		public void handle(HttpExchange exchange) throws IOException {
			
			String respuesta = "aaa no seee";
			
			Cors.controlCors(exchange);
			
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
					Compania com = gson.fromJson(body, Compania.class);
					
					DataCompania.actualizanombre(com.getId(), com.getNombre());
					
			    	respuesta = "todo bem";
			    	exchange.sendResponseHeaders(200, respuesta.getBytes().length);

		    	
		    }
		    catch(Error e ) {
		    	
		    	respuesta = "Error";
		    	exchange.sendResponseHeaders(401, respuesta.getBytes().length);
		    	
		    	
		    }
		    	
		    	
		    OutputStream os = exchange.getResponseBody();
            os.write(respuesta.getBytes(StandardCharsets.UTF_8));
            os.close();
			
		    	
		    }
	}
	
	public static class crearcompania implements HttpHandler {
		
		
		public void handle(HttpExchange exchange) throws IOException {
			
			String mensaje = "funcionando";
			Boolean Existe_empresa = false;
			
			
			Cors.controlCors(exchange);
			
			
			if (exchange.getRequestMethod().equals("OPTIONS")) {
		        exchange.sendResponseHeaders(204, -1);
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
		    	
		    	System.out.println(e);
		    	mensaje = "Error";
		    	
		    	exchange.sendResponseHeaders(401, mensaje.getBytes().length);
		    	
		    }
			
			
			
			
			try {
			InputStream is = exchange.getRequestBody();
			String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			
			Gson gson = new Gson();
			Compania com = gson.fromJson(body, Compania.class);	
			
			Existe_empresa = DataCompania.validarempresaexistente(com.getNombre());
		
			
			if (!Existe_empresa) {
				
				DataCompania.crearcompania(com.getNombre());
				
				
				exchange.sendResponseHeaders(200, mensaje.getBytes().length);
				OutputStream os = exchange.getResponseBody();
	            os.write(mensaje.getBytes()); 
	            os.close();
			}else {
				
				mensaje = "Empresa duplicada";
				exchange.sendResponseHeaders(409, mensaje.getBytes().length);
				OutputStream os = exchange.getResponseBody();
	            os.write(mensaje.getBytes()); 
	            os.close();
	           System.out.println("Empresa repetida");
				
			}
			
			}catch(SQLException e) {
				
				
				

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
	
	
	
	
	
	public static LinkedList<Compania> recuperarTodos() {
		LinkedList<Compania> companias = new LinkedList<>();
		try {
			// crear una conexión
			Connection conn = Conexion.getInstancia().getConn();

			// ejecutar la query
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery("select * from compania");

            // mapear de resultset a objeto
            while(rs.next()) {
            	Compania c=new Compania();
                c.setId(rs.getInt("idcompania"));
                c.setNombre(rs.getString("nombre"));

                companias.add(c);

            }
            //cerrar recursos
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}

		    conn.close();
		    
		    /* mostrar info
		    System.out.println("Listado Completo");
		    System.out.println(companias);
		    System.out.println();System.out.println();
		    */
		    
		    

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return companias;
		
	}
	
	public static Compania recuperarPorId(int id) {		
		Compania c = null;

		try {
			// crear una conexión
			Connection conn = Conexion.getInstancia().getConn();

			// definir la query
            PreparedStatement stmt = conn.prepareStatement("select * from compania where idcompania=?");
            
            // setear el/los parámetros
            stmt.setInt(1, id);

            

            // ejecutar query y obtener resultados
            ResultSet rs= stmt.executeQuery();

            // mapear de resultset a objeto
            if(rs.next()) {
        		c=new Compania();
                c.setId(rs.getInt("idcompania"));
                c.setNombre(rs.getString("nombre"));
                c.setEstado(rs.getString("estado"));
            }
            //cerrar recursos
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}

		    conn.close();
		    

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return c;
	}
	
	public static void insertarNuevo(String nombre) {
		Compania compania= new Compania();
		
		compania.setNombre(nombre);
		
		try {
			Connection conn = Conexion.getInstancia().getConn();
			// definir la query
            PreparedStatement pstmt = conn.prepareStatement(
            		"insert into compania(nombre) values (?)"
            		,PreparedStatement.RETURN_GENERATED_KEYS
            		);
            
            
            pstmt.setString(1, compania.getNombre());

            pstmt.executeUpdate();
            
            ResultSet keyResultSet=pstmt.getGeneratedKeys();

            if(keyResultSet!=null && keyResultSet.next()) {
                    int id=keyResultSet.getInt(1);
                    System.out.println("ID: "+id);
                    compania.setId(id);
            }


            if(keyResultSet!=null){keyResultSet.close();}
            if(pstmt!=null){pstmt.close();}

		    conn.close();
		    
		    // mostrar objeto
		    System.out.println("Nueva Compania");
		    System.out.println(compania);
		    System.out.println();System.out.println();

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}