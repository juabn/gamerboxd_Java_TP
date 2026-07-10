package servidor;
import entities.Compania;
import entities.Persona;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

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

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import data.Conexion;
import data.DataCompania;
import data.Data_persona;

public class AbmcCompania {
	//ABMC Compania USOS
	
	//LinkedList<Compania> companias = AbmcCompania.recuperarTodos();
	//Compania compania = AbmcCompania.recuperarPorId(10);
	//AbmcCompania.insertarNuevo("Warner Bros");
	
	
	//metodo para controlar cors
		public static void controlCors(HttpExchange exchange) {
			
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
		    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
		}
	
	
	public static class bajalogicacompania implements HttpHandler  {
		
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
					Compania per = gson.fromJson(body, Compania.class);
					
					DataCompania.dardebaja(per.getId());
					
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
	
	public static String actualizarnombrecompania() {
		
		String respuesta = "";
		
		return respuesta;
	}
	
	public static String crearcompania() {
		
		
		String respuesta = "";
		
		return respuesta;
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
            }
            //cerrar recursos
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}

		    conn.close();
		    
		    // mostrar objeto
		    System.out.println("Buscar por id");
		    System.out.println(c);
		    System.out.println();System.out.println();

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