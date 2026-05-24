package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import data.Conexion;
import entities.Grupo;

public class AbmcGrupo {
	
	public static LinkedList<Grupo> recuperarTodos() {
		LinkedList<Grupo> grupos = new LinkedList<>();
		try {
			// crear una conexión
			Connection conn = Conexion.getInstancia().getConn();

			// ejecutar la query
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery("select * from grupo");

            // mapear de resultset a objeto
            while(rs.next()) {
            	Grupo g=new Grupo();
                g.setId(rs.getInt("idgrupo"));
                g.setNombre(rs.getString("nombre"));
                g.setFoto_perfil(rs.getString("foto_perfil"));
                g.setDescripcion(rs.getString("descripcion"));

                grupos.add(g);

            }
            //cerrar recursos
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}

		    conn.close();
		    
		    // mostrar info
		    System.out.println("Listado Completo");
		    System.out.println(grupos);
		    System.out.println();System.out.println();
		    
		    
		    

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return grupos;
		
	}
	
	public static Grupo recuperarPorId(int id) {		
		Grupo g = null;

		try {
			// crear una conexión
			Connection conn = Conexion.getInstancia().getConn();

			// definir la query
            PreparedStatement stmt = conn.prepareStatement("select * from grupo where idgrupo=?");
            
            // setear el/los parámetros
            stmt.setInt(1, id);

            

            // ejecutar query y obtener resultados
            ResultSet rs= stmt.executeQuery();

            // mapear de resultset a objeto
            if(rs.next()) {
        		g=new Grupo();
                g.setId(rs.getInt("idgrupo"));
                g.setNombre(rs.getString("nombre"));
            }
            //cerrar recursos
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}

		    conn.close();
		    
		    // mostrar objeto
		    System.out.println("Buscar por id");
		    System.out.println(g);
		    System.out.println();System.out.println();

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return g;
	}
	
	public static void insertarNuevo(String foto_perfil,String nombre,  String descripcion) {
		Grupo grupo= new Grupo();
		
		grupo.setNombre(nombre);
		grupo.setFoto_perfil(foto_perfil);
		grupo.setDescripcion(descripcion);
		
		try {
			Connection conn = Conexion.getInstancia().getConn();
			// definir la query
            PreparedStatement pstmt = conn.prepareStatement(
            		"insert into grupo(foto_perfil,nombre,descripcion) values (?,?,?)"
            		,PreparedStatement.RETURN_GENERATED_KEYS
            		);
            
            
            pstmt.setString(1, grupo.getFoto_perfil());
            pstmt.setString(2, grupo.getNombre());
            pstmt.setString(3, grupo.getDescripcion());
            pstmt.executeUpdate();
            
            ResultSet keyResultSet=pstmt.getGeneratedKeys();

            if(keyResultSet!=null && keyResultSet.next()) {
                    int id=keyResultSet.getInt(1);
                    System.out.println("ID: "+id);
                    grupo.setId(id);
            }


            if(keyResultSet!=null){keyResultSet.close();}
            if(pstmt!=null){pstmt.close();}

		    conn.close();
		    
		    // mostrar objeto
		    System.out.println("Nuev Grupo");
		    System.out.println(grupo);
		    System.out.println();System.out.println();

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
