package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Conexion;
import entities.Resenia;

public class AbmcResenia {

	public static List<Resenia> recuperarPorIdJuego(int id) {
		List<Resenia> lista = new ArrayList<>();

		try {
			// crear una conexión
			Connection conn = Conexion.getInstancia().getConn();

			// definir la query
			PreparedStatement stmt = conn.prepareStatement("select * from resenia where id_juego=?");

			// setear el/los parámetros
			stmt.setInt(1, id);

			// ejecutar query y obtener resultados
			ResultSet rs = stmt.executeQuery();

			// mapear cada fila del resultset a un objeto y agregarlo a la lista
			while (rs.next()) {
				Resenia r = new Resenia();
				r.setId_juego(rs.getInt("id_juego"));
				r.setFecha(rs.getString("fecha"));
				r.setHora(rs.getString("hora"));
				r.setTitulo(rs.getString("titulo"));
				r.setDescripcion(rs.getString("descripcion"));
				r.setPuntaje(rs.getFloat("puntaje"));
				r.setMail_usuario(rs.getString("mail_usuario"));
				lista.add(r);
			}

			// cerrar recursos
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
			conn.close();

			// mostrar objetos
			System.out.println("Buscar por id juego");
			System.out.println();
			System.out.println();

		} catch (SQLException ex) {
			// Manejo de errores
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return lista;
	}
}
