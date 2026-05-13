package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import entities.Compania;
import entities.Juego;
import entities.Plataforma;


public class DataJuego {

	
	public void guardarJuegoCompleto(Juego juego) {
	    Connection conn = ConnectionManager.getConnection(); // Tu clase de conexión
	    
	    try {
	        // 1. Persistir Plataformas y Compañías primero
	        for (Plataforma p : juego.getPlataformas()) {
	        	DataPlataforma dp = new DataPlataforma();
	            dp.persistirPlataforma(p, conn);
	        }
	        for (Compania c : juego.getCompanias()) {
	        	DataCompania dc = new DataCompania();
	            dc.persistirCompania(c, conn);
	        }

	        String sqlJuego = "INSERT IGNORE INTO juego (idjuego, titulo, descripcion, imagen) VALUES (?, ?, ?, ?)";
	        PreparedStatement stmtJuego = conn.prepareStatement(sqlJuego);
	        stmtJuego.setInt(1, juego.getId_juego());
	        stmtJuego.setString(2, juego.getTitulo());
	        stmtJuego.setString(3, juego.getDescripcion());
	        stmtJuego.setString(4, juego.getImagen());
	        stmtJuego.executeUpdate();

	        // 3. Persistir Relaciones en tablas intermedias
	       // guardarRelaciones(juego, conn);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
