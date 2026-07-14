package data;

import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.*;
import entities.Compania;
import entities.Juego;
import entities.Plataforma;


public class DataJuego {

public static boolean buscarjuego(String nombreJuego) {
		
		boolean resultado = false;
		
		try {
			
			String nombremin = nombreJuego.replace(" ", "").toLowerCase();
			
			Connection conn = Conexion.getInstancia().getConn();
			String query = "SELECT 1 FROM propuesta WHERE LOWER(REPLACE(nombrejuego, ' ', '')) = ?";
			PreparedStatement Resultado = conn.prepareStatement(query);
			Resultado.setString(1, nombremin);
			ResultSet rs = Resultado.executeQuery();
			
			if (rs.next()) {		
				resultado = true;
			}
			
			
			
			
		}catch(SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
			
		}
		
		
		return resultado;
		
	}
}

