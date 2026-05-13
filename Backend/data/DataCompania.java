package data;

import java.sql.*;
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
}
