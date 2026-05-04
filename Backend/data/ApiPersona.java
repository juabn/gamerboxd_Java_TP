package data;

import java.sql.*;

public class ApiPersona {
	public static void main(String[] args) {
		
		
		
		actualizar_contrasenia();
		
		
		
	}
	
	
	
	
	private static void actualizar_contrasenia() {
try {
			
			String url = "jdbc:mysql://localhost:3306/gamerboxd";
			String usuario = "root"; 
		    String password = "12345";
		    Connection conn = DriverManager.getConnection(url, usuario, password);
		    
		    
		    String query = "update persona set contrasenia = ? where idpersona = ?";
		    PreparedStatement ps = conn.prepareStatement(query);
		    ps.setString(1, "12345678910");
		    ps.setString(2, "1");
		    
		    ps.executeUpdate();
		    
}
		
catch(SQLException ex){
	
	
	System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
}
		
		
		
		
		
		
	}
	
	private static void insertar_persona() {
		
		
		try {
			
			String url = "jdbc:mysql://localhost:3306/gamerboxd";
			String usuario = "root"; 
		    String password = "12345";
		    Connection conn = DriverManager.getConnection(url, usuario, password);
		    
		    
		    String query = "insert into persona (nombre, contrasenia,mail,foto_perfil,rol) values (?,?,?,?,?)";
		    PreparedStatement statement = conn.prepareStatement(query);
		    statement.setString(1, "probando");
		    statement.setString(2, "probando");
		    statement.setString(3, "probando");
		    statement.setString(4, "probando");
		    statement.setString(5, "probando");
		    
		    statement.executeUpdate();
		}
		
		
		
		catch(SQLException ex){
			
			
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		
		
	}
	
	private static void obtener_todos() {
			
			try {
				
				String url = "jdbc:mysql://localhost:3306/gamerboxd";
				String usuario = "root"; 
			    String password = "12345";
			    Connection conn = DriverManager.getConnection(url, usuario, password);
			    
			    
			    
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

