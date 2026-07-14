package data;

import java.sql.*;
import java.util.ArrayList;

import entities.Compania;

public class DataPropuesta {
	
	
	public static boolean insertarpropuesta (String nombrejuego, String descripcion, String mailusuario, ArrayList<Compania> companias, String foto) {
		
		boolean respuesta = false;
		
		
		
		try {

		Connection conn = Conexion.getInstancia().getConn();
		
		String query1 = "insert into propuesta (nombrejuego, imagen, descripcionjuego, mail_usuario) values (?,?,?,?)";
		PreparedStatement ps1 = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
		ps1.setString(1, nombrejuego);
		ps1.setString(2, descripcion);
		ps1.setString(3, descripcion);
		ps1.setString(4, mailusuario);
		ps1.executeUpdate();
		
		ResultSet rs = ps1.getGeneratedKeys();
		int idPropuestaGenerado = 0;
		
		if (rs.next()) {
            idPropuestaGenerado = rs.getInt(1); 
        }
		
		try {
			
			String query2 = "insert into juego_compania (id_comp, id_propuesta,) values (?,?)";
			PreparedStatement ps2 = conn.prepareStatement(query2);
			
			ps2.setInt(2, idPropuestaGenerado);
			
			for(Compania comp: companias) {
				
				ps2.setInt(1, comp.getId());
				ps2.executeUpdate();
				
			}
			
			respuesta = true;
			
			
		}catch(SQLException e) {
			
			respuesta = false;
			
			
		}
		
		}
		
		catch(SQLException e) {
			
			respuesta = false;
			
		}
		
		
		
		
		return respuesta;
		
		
		
		
		
		
	}
	
	
	
	

}
