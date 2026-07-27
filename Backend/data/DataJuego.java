package data;

import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.*;
import entities.Compania;
import entities.Juego;
import entities.Plataforma;
import entities.Propuesta;


public class DataJuego {

public static boolean buscarjuego(String nombreJuego) {
		
		boolean resultado = false;
		
		try {
			
			String nombremin = nombreJuego.replace(" ", "").toLowerCase();
			System.out.println(nombremin);
			
			Connection conn = Conexion.getInstancia().getConn();
			String query = "SELECT 1 FROM juego WHERE LOWER(REPLACE(titulo, ' ', '')) = ?";
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



public static boolean insertarjuego(Propuesta pro) {
	
	boolean resultado = false;
	
	try {
	
	
	Connection conn = Conexion.getInstancia().getConn();
    
    String query = "insert into juego(titulo, imagen, descripcion) values (?,?,?)";
    PreparedStatement statement = conn.prepareStatement(query);
    
    
    statement.setString(1, pro.getNombreJuego());
    statement.setString(2, pro.getFoto());
    statement.setString(3, pro.getDescripcionjuego());
    
    statement.executeUpdate();
    
    resultado = true;

	}
	
	catch(SQLException ex){
		
		System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
		
		
	}
	
	return resultado;
}
}

