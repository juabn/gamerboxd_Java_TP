package data;

import java.sql.*;
import java.util.ArrayList;

import entities.Compania;

public class DataPropuesta {
	
	
	public static boolean insertarpropuesta (String nombrejuego, String descripcion, String mailusuario, ArrayList<Compania> companias, String foto) {
		
		boolean respuesta = false;
		Connection conn = null;
		PreparedStatement ps1 = null;
	    PreparedStatement ps2 = null;
	    ResultSet rs = null;
		
		
		
		try {

		conn = Conexion.getInstancia().getConn();
		
		conn.setAutoCommit(false);
		
		String query1 = "insert into propuesta (nombrejuego, imagen, descripcionjuego, mail_usuario) values (?,?,?,?)";
		ps1 = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
		
		ps1.setString(1, nombrejuego);
		ps1.setString(2, foto);
		ps1.setString(3, descripcion);
		ps1.setString(4, mailusuario);
		ps1.executeUpdate();
		
		 rs = ps1.getGeneratedKeys();
		int idPropuestaGenerado = 0;
		
		if (rs.next()) {
            idPropuestaGenerado = rs.getInt(1); 
        }
		
		try {
			
			String query2 = "insert into compania_propuesta (id_comp, id_propuesta) values (?,?)";
			ps2 = conn.prepareStatement(query2);
			
			ps2.setInt(2, idPropuestaGenerado);
			
			for(Compania comp: companias) {
				
				ps2.setInt(1, comp.getId());
				ps2.executeUpdate();
				
			}
			
			conn.commit();
            respuesta = true;
			
			
			respuesta = true;
			
			
		}catch(SQLException e) {
			
			System.out.println(e);
			if (conn != null) {
                conn.rollback();
            }
            
			respuesta = false;
			
			
			
		}
		
		}
		
		catch(SQLException e) {
			
			
			respuesta = false;
			System.out.println(e);
			
			
		}
		finally {
	        
	        try {
	            if (conn != null) conn.setAutoCommit(true);
	            if (rs != null) rs.close();
	            if (ps1 != null) ps1.close();
	            if (ps2 != null) ps2.close();
	        } catch (SQLException ex) {
	            System.out.println("Error al cerrar recursos: " + ex.getMessage());
	        }
		
		
		
		}
		return respuesta;
		
		
		
		
		
		
	}
	
	
	
	
	
}
