package data;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

import entities.Compania;
import servidor.GestionMail;

public class DataCompania {
	
	
	
	
	
	public void persistirCompania(Compania c, Connection conn) {
	    String sql = "INSERT IGNORE INTO compania (idcompania, nombre) VALUES (?, ?)";
	    
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, c.getId());
	        stmt.setString(2, c.getNombre());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        System.err.println("Error al persistir compañía: " + c.getNombre() + " - " + e.getMessage());
	    }
	}
	
	
	
	
	public static String dardebaja(int id) {
		
		String Respuesta = "ok";
		

		try {
			
			Connection conn = Conexion.getInstancia().getConn();
		    
		    
		    String query = "update compania set estado = ? where idcompania = ?";
		    PreparedStatement ps = conn.prepareStatement(query);   
		    ps.setString(1, "inactivo");
		    ps.setInt(2, id);
		    ps.executeUpdate();
			
	   
		    
		}catch(SQLException ex){
	
	
		System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	    Respuesta = "Error en bd";
		}
		
		
		
		
		
		
		return Respuesta;
}
	

	public static String actualizanombre(int id, String nombre) {
		
		String Respuesta = "ok";
		

		try {
			
			Connection conn = Conexion.getInstancia().getConn();
		    
		    
		    String query = "update compania set nombre = ? where idcompania = ?";
		    PreparedStatement ps = conn.prepareStatement(query);   
		    ps.setString(1, nombre);
		    ps.setInt(2, id);
		    ps.executeUpdate();
			
	   
		    
		}catch(SQLException ex){
	
	
		System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	    Respuesta = "Error en bd";
		}
		
		
		
		
		
		
		return Respuesta;
}

	
	
public static String  crearcompania(String nombre) throws SQLException{
		
	String Respuesta = "No se";
	


	Connection conn = Conexion.getInstancia().getConn();
	
	String query1 = "insert into compania (nombre) values (?)";
	PreparedStatement ps1 = conn.prepareStatement(query1);
	ps1.setString(1, nombre);
	ps1.executeUpdate();
	Respuesta = "ok";
	    	

	return Respuesta;
		
}



}