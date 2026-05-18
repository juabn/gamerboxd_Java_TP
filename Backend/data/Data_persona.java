//ESTO NO ES UNA API NO SE POR QUE LE PUSE ESE NOMBRE JEJE DESPUES LO CAMBIO

package data;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.security.SecureRandom;
import java.util.Base64;
import servidor.GestionMail;
import java.util.Random;


import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;

public class Data_persona {
	public static void main(String[] args) {
        


    }
	
	
	public static Boolean veriToken(String token, String mail) {
		
		Boolean ok = false;
		
		try {
			
			Connection conn = Conexion.getInstancia().getConn();
			String query = "SELECT codigo FROM recuperacion_password WHERE id IN (SELECT MAX(id) FROM recuperacion_password  WHERE mail_persona = ?  GROUP BY mail_persona)";
			PreparedStatement ps = conn.prepareStatement(query);
	    	ps.setString(1, mail);
	    	ResultSet rs = ps.executeQuery();
	    	
	    	if (rs.next()) {
	    		
	    		String token_real = rs.getString("codigo");
	    		if (token_real.equals(token)) {
	    			
	    			ok = true;
					
				}
	    		
	    	}
			
			
			
		}catch(SQLException ex){
			
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
			
		}
		
		
		return ok;
		
		
		
		
		
		
	}
	
	
	//Genera token de cambio de password, lo guarda en la bd y lo envia por mail
	public static String enviar_token(String mail) {
		
		String token = "Error";
		
		try {
			
			Connection conn = Conexion.getInstancia().getConn();
			String query = "select * from persona where mail = ?";
		    PreparedStatement ps = conn.prepareStatement(query);
		    ps.setString(1, mail);
		    ResultSet rs = ps.executeQuery();
		    
		    if (rs.next()) {
		    	
		    	Random random = new Random();
		    	int numeroMinMax = random.nextInt(1000, 9999);
		    	token = String.valueOf(numeroMinMax);
		    	
		    	
		    	
		        LocalDateTime fecha_hora_vencimiento = LocalDateTime.now().plusMinutes(10);
		    	
		    	String query1 = "insert into recuperacion_password (codigo,fecha_expiracion, mail_persona) values  (?, ?, ?)";
		    	PreparedStatement ps1 = conn.prepareStatement(query1);
		    	ps1.setString(1, token);
		    	ps1.setObject(2, fecha_hora_vencimiento);
		    	ps1.setString(3, mail);
		    	ps1.executeUpdate();
		    	GestionMail.enviarmail(mail, "nuevacontrasenia", "El token de recuperacion de contrasenia es: " + token);
 	
				
			}else {
				
				token = "No existe mail";
				
			}
					
				
			
		}
		catch(SQLException ex){
			
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
			
		}
		
		
		return token;
		
		
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

