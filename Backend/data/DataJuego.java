package data;

import java.util.ArrayList;
import java.sql.*;
import entities.Compania;
import entities.Juego;
import entities.Plataforma;


public class DataJuego {

	public void registrarJuegoCompleto(Juego j) {
        Connection conn = Conexion.getInstancia().getConn();

        try {
            
            
            DataPlataforma dp = new DataPlataforma();
            for (Plataforma p : j.getPlataformas()) {
                dp.persistirPlataforma(p, conn);
            }

            DataCompania dc = new DataCompania();
            for (Compania c : j.getCompanias()) {
                dc.persistirCompania(c, conn);
            }

            
            String sqlJuego = "INSERT IGNORE INTO juego (idjuego, titulo, imagen, descripcion) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmtJ = conn.prepareStatement(sqlJuego)) {
                stmtJ.setInt(1, j.getId_juego());
                stmtJ.setString(2, j.getTitulo());
                stmtJ.setString(3, j.getImagen());
                stmtJ.setString(4, j.getDescripcion());
                stmtJ.executeUpdate();
            }

            persistirRelaciones(j, conn);

            System.out.println("huego guardado con exito: " + j.getTitulo());

        } catch (SQLException e) {
            System.err.println("error al registrar juego " + j.getTitulo() + ": " + e.getMessage());
        }
    }
	
	private void persistirRelaciones(Juego j, Connection conn) throws SQLException {
	    
	    String sqlPlat = "INSERT IGNORE INTO plataforma_juego (idjuego, idplataforma) VALUES (?, ?)";
	    try (PreparedStatement st = conn.prepareStatement(sqlPlat)) {
	        for (Plataforma p : j.getPlataformas()) {
	            st.setInt(1, j.getId_juego());
	            st.setInt(2, p.getId());
	            st.executeUpdate();
	        }
	    }

	   
	    String sqlComp = "INSERT IGNORE INTO juego_compania (idjuego, id_comp) VALUES (?, ?)";
	    try (PreparedStatement st = conn.prepareStatement(sqlComp)) {
	        for (Compania c : j.getCompanias()) {
	            st.setInt(1, j.getId_juego());
	            st.setInt(2, c.getId());
	            st.executeUpdate();
	        }
	    }
	}
}
