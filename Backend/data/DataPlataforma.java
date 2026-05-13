package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Plataforma;

public class DataPlataforma {
	public void persistirPlataforma(Plataforma p, Connection conn) {
	    // si existe lo ignora
	    String sql = "INSERT IGNORE INTO plataforma (idplataforma, nombre) VALUES (?, ?)";
	    
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, p.getId());
	        stmt.setString(2, p.getNombre());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.err.println("Error al persistir plataforma: " + p.getNombre() + " - " + e.getMessage());
	    }
	}
}
