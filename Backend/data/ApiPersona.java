package data;
import java.sql.*;

public class ApiPersona {
	public static void main(String[] args) {




        try {

            String url = "jdbc:mysql://localhost:3306/gamerboxd";

            String usuario = "root"; 
            String password = "santigay123!";


            Connection conn = DriverManager.getConnection(url, usuario, password);
            System.out.println("funca");
        }


        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        


    }

}
