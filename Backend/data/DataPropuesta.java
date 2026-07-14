package data;

import java.sql.*;
import java.util.ArrayList;

public class DataPropuesta {
	
	
	public static boolean insertarpropuesta (String nombrejuego, String descripcion, String mailusuario, ArrayList<String> companias, String foto) {
		
		boolean respuesta = false;
		
		
		
		try {

		Connection conn = Conexion.getInstancia().getConn();
		
		String query1 = "insert into propuesta (nombrejuego, imagen, descripcionjuego, mail_usuario) values (?,?,?,?)";
		PreparedStatement ps1 = conn.prepareStatement(query1);
		ps1.setString(1, nombrejuego);
		ps1.setString(2, descripcion);
		ps1.setString(3, descripcion);
		ps1.setString(4, mailusuario);
		ps1.executeUpdate();
		
		}
		
		catch(SQLException e) {
			
			
			
		}
		
		
		
		
		return respuesta;
		
		
		
		
		
		
	}
	
	
	
	

}
