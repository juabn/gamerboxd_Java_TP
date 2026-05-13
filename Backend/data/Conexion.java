package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static Conexion instancia;
    private Connection conn;

    private Conexion() {
        String url = "jdbc:mysql://localhost:3306/gamerboxd";
        String usuario = "root"; 
        String password = "santigay123!"; //va tu password
        try {
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConn() {
        return conn;
    }
}
