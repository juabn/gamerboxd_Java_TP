package data;

import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;

import entities.Compania;

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
	




}