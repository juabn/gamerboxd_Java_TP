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
import entities.Juego;
import entities.Persona;
import entities.Propuesta;

public class DataGrupo {

	
	
	
	public static Grupo buscarGrupoPorMiembro (String mail) {
		
		Grupo g = null;
		ArrayList<Persona> miembros = new ArrayList<>();

		try {
			
			Connection conn = Conexion.getInstancia().getConn();

			
	        PreparedStatement stmt = conn.prepareStatement("select * from persona inner join grupo on grupo.idgrupo = persona.idgrupo where persona.mail = ?");
	        PreparedStatement stmt2 = conn.prepareStatement("select * from persona where idgrupo = ? and estado = ?");
	        
	        
	        stmt.setString(1, mail);

	        ResultSet rs= stmt.executeQuery();


	        if(rs.next()) {
	    		g=new Grupo();
	    		g.setDescripcion(rs.getString("descripcion"));
	    		g.setFoto_perfil(rs.getString("foto_perfil"));
	    		g.setNombre(rs.getString("nombre"));
	    		
	    		stmt2.setString(1, rs.getString("idgrupo"));
	    		stmt2.setString(1, rs.getString("Activo"));
	    		ResultSet rs2= stmt.executeQuery();
	    		
	    		while (rs2.next()) {
	    			
	    			Persona per = new Persona();
	    			per.setNombre_usuario(rs2.getString("nombre"));
			
	    			miembros.add(per);
				}	
	    		
	    		g.setIntegrantes(miembros);


	        }
	        
	        if(rs!=null){rs.close();}
	        if(stmt!=null){stmt.close();}

		    
		    

		} catch (SQLException ex) {
		
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return g;
		
		
	}
	
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
