//ESTO NO ES UNA API NO SE POR QUE LE PUSE ESE NOMBRE JEJE DESPUES LO CAMBIO

package data;
import java.sql.*;
import java.security.SecureRandom;
import java.util.Base64;
import servidor.GestionMail;


import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;

public class Data_persona {
	public static void main(String[] args) {
        


    }
	
	
	
	public static String recuperar_contrasenia(String mail) {
		
		String contrasenia = "Error";
		
		try {
			
			Connection conn = Conexion.getInstancia().getConn();
			String query = "select * from persona where mail = ?";
		    PreparedStatement ps = conn.prepareStatement(query);
		    ps.setString(1, mail);
		    ResultSet rs = ps.executeQuery();
		    
		    if (rs.next()) {
		    	
		    	
		    	SecureRandom random = new SecureRandom();
		        byte[] bytes = new byte[24]; 
		        random.nextBytes(bytes);
		        
		        // Lo convertimos a una cadena legible (letras, números y caracteres como + o /)
		        contrasenia = Base64.getEncoder().encodeToString(bytes);
		    	
		    	String query1 = "update persona set contrasenia = ? where mail = ?";
		    	PreparedStatement ps1 = conn.prepareStatement(query1);
		    	ps1.setString(1, contrasenia);
		    	ps1.setString(2, mail);
		    	ps1.executeUpdate();
		    	GestionMail.enviarmail(mail, "nuevacontrasenia", "su nueva contrsenia es: " + contrasenia);
 	
				
			}else {
				
				contrasenia = "No existe mail";
				
			}
					
			
			
			
			
			
			
		}
		catch(SQLException ex){
			
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
			
		}
		
		
		return contrasenia;
		
		
	}
		


	
	
	public static Boolean buscar_persona(String mail, String contrasenia) {
		
		Boolean resultado = false;
		
try {
		
		Connection conn = Conexion.getInstancia().getConn();
	    
	    String query = "select * from persona where mail = ?";
	    PreparedStatement ps = conn.prepareStatement(query);
	    ps.setString(1, mail);
	    ResultSet rs = ps.executeQuery();
	    
	    if (rs.next()) {
	    	
	    	resultado = BCrypt.checkpw(contrasenia,rs.getString("contrasenia"));
	    	
	    	
			
		}
	    
	        
	   
	    
	}
catch(SQLException ex){
	
	
	System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
}
		
return resultado;
		
	}

	
	
	
	private static void actualizar_contrasenia() {
		
try {
			
			Connection conn = Conexion.getInstancia().getConn();
		    
		    
		    String query = "update persona set contrasenia = ? where idpersona = ?";
		    PreparedStatement ps = conn.prepareStatement(query);
		    
		    
		    int logRounds = 12;
		    String salt = BCrypt.gensalt(logRounds);
		    String password2 = "123";
		    String hashedPassword = BCrypt.hashpw(password2, salt);	    
		    
		    ps.setString(1, hashedPassword);
		    ps.setString(2, "1");
		    ps.executeUpdate();
		    
}
		
catch(SQLException ex){
	
	
	System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
}
		
		
	
		
		
	}
	
	public static void insertar_persona(String valor1, String valor2, String mail, String rol, String imagen ) throws SQLException {
		
		
		
			
			
			Connection conn = Conexion.getInstancia().getConn();
		    
		    String query = "insert into persona (nombre, contrasenia,mail,foto_perfil,rol) values (?,?,?,?,?)";
		    PreparedStatement statement = conn.prepareStatement(query);
		    
		    
		    int logRounds = 12;
		    String salt = BCrypt.gensalt(logRounds);
		    String hashedPassword = BCrypt.hashpw(valor2, salt);
		    
		    statement.setString(1, valor1);
		    statement.setString(2, hashedPassword);
		    statement.setString(3, mail);
		    statement.setString(4, imagen);
		    statement.setString(5, rol);
		    
		    statement.executeUpdate();
		
		
		
		
		
			
		
		
		
		
		
	}
	
	private static void obtener_todos() {
			
			try {
				
				Connection conn = Conexion.getInstancia().getConn();
			    
			    
			    
				String query = "select * from persona";
				PreparedStatement Resultado = conn.prepareStatement(query);
				ResultSet rs = Resultado.executeQuery();
				
				
				while (rs.next()) {
					
					String nombre = rs.getString("nombre");
					
					System.out.println(nombre);
					
				}
				
				
				
				
				
		
			}
			
			
			catch(SQLException ex){
				
				
				System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
			
			
			
			
		
		
		
		

}
}

