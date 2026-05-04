package data;

import java.sql.*;

public class ApiPersona {
	public static void main(String[] args) {
		
		
		obtener_todos();
		
		
		
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

