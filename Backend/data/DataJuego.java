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


public static Juego recuperarPorTitulo(String titulo) {		
	Juego j = null;

	try {
		
		Connection conn = Conexion.getInstancia().getConn();

		
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM juego WHERE LOWER(REPLACE(titulo, ' ', '')) = ?");
        
        String tituloNormalizado = titulo.replace(" ", "").toLowerCase();
        stmt.setString(1, tituloNormalizado);

       

      
        ResultSet rs= stmt.executeQuery();


        if(rs.next()) {
    		j=new Juego();
    		j.setTitulo(rs.getString("titulo"));
    		j.setDescripcion(rs.getString("descripcion"));
    		j.setId_juego(rs.getString("idjuego"));
    		j.setEstado(rs.getString("estado"));

        }
        
        if(rs!=null){rs.close();}
        if(stmt!=null){stmt.close();}

	    
	    

	} catch (SQLException ex) {
	
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	return j;
}



public static boolean insertarjuego(Propuesta pro) {
	
	boolean resultado = false;
    Connection conn = null;
    
   
	
	try {
	
	
	conn = Conexion.getInstancia().getConn();
	
	conn.setAutoCommit(false);
    
    String query = "insert into juego(titulo, imagen, descripcion) values (?,?,?)";
    PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    
    
    statement.setString(1, pro.getNombreJuego());
    statement.setString(2, pro.getFoto());
    statement.setString(3, pro.getDescripcionjuego());
    
    statement.executeUpdate();
    
    ResultSet rsKeys = statement.getGeneratedKeys();
    int idJuegoGenerado = 0;
    if (rsKeys.next()) {
        idJuegoGenerado = rsKeys.getInt(1);
    }
    statement.close();
    
    
    String queryRelacion = "insert into juego_compania(idjuego, id_comp) values (?,?)";
    PreparedStatement psRelacion = conn.prepareStatement(queryRelacion);
    
    for (Compania comp : pro.getCompaniasJuego()) {
        psRelacion.setInt(1, idJuegoGenerado);
        psRelacion.setInt(2, comp.getId()); 
        
        psRelacion.executeUpdate();
        
    }

    
    conn.commit();
    resultado = true;
	}
	
	
	
	catch(SQLException ex){
		
		if (conn != null) {
            try {
                conn.rollback();
                System.out.println("Transacción revertida (Rollback realizado).");
            } catch (SQLException rollbackEx) {
                System.out.println("Error en rollback: " + rollbackEx.getMessage());
            }
        }
        
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
		
		
	}
	
	return resultado;
}
}

