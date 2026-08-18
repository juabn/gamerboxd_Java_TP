package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.mindrot.jbcrypt.BCrypt;

import entities.Compania;
import entities.Grupo;
import entities.Propuesta;

public class DataGrupo {

	
	public static boolean insertarMiembro(int idgrupo, String mail) {
		
		boolean resultado = false;
		
		try {
			Connection conn = Conexion.getInstancia().getConn();
		    
		    
		    String query = "update persona set idgrupo = ?, rolgrupo = ? where mail = ?";
		    PreparedStatement ps = conn.prepareStatement(query);
		    
		    	    
		    
		    ps.setInt(1,idgrupo);
		    ps.setString(2, "miembro");
		    ps.setString(3, mail);
		    ps.executeUpdate();
		    
		    resultado = true;
	
	
		}catch (SQLException ex) {
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	    }
	
	return resultado;
	
}
	
	
	
public static LinkedList<Grupo> listargrupos() {
	
	LinkedList<Grupo> grupos = new LinkedList<>();


	try {
		// crear una conexión
		Connection conn = Conexion.getInstancia().getConn();

		
		String query = "select * from grupo";
	    PreparedStatement ps = conn.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();

        // mapear de resultset a objeto
        while(rs.next()) {
        	
        	
        	Grupo gru =new Grupo();
        	gru.setDescripcion(rs.getString("descripcion"));
        	gru.setFoto_perfil(rs.getString("foto_perfil"));
        	gru.setNombre(rs.getString("nombre"));
        	gru.setId(rs.getInt("idgrupo"));

        	 
            grupos.add(gru);

        }

	    
	    

	} catch (SQLException ex) {
	    // Manejo de errores
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}
	return grupos;
	
	
}
	
	
	
}
