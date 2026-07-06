package servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Grupo;
import entities.Plataforma;

import data.Conexion;

public class AbmcPlataforma {
	//ABMC Plataforma USOS
	//LinkedList<Plataforma> plataformas = AbmcPlataforma.recuperarTodos();
	//Plataforma plataforma = AbmcPlataforma.recuperarPorId(1);
	//AbmcPlataforma.insertarNuevo("Gamecube");
	public static LinkedList<Plataforma> recuperarTodos() {
		LinkedList<Plataforma> companias = new LinkedList<>();
		try {
			// crear una conexión
			Connection conn = Conexion.getInstancia().getConn();

			// ejecutar la query
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery("select * from plataforma");

            // mapear de resultset a objeto
            while(rs.next()) {
            	Plataforma c=new Plataforma();
                c.setId(rs.getInt("idplataforma"));
                c.setNombre(rs.getString("nombre"));

                companias.add(c);

            }
            //cerrar recursos
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}

		    conn.close();
		    
		    // mostrar info
		    System.out.println("Listado Completo");
		    System.out.println(companias);
		    System.out.println();System.out.println();
		    
		    
		    

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return companias;
		
	}
	
	public static Plataforma recuperarPorId(int id) {		
		Plataforma p = null;

		try {
			// crear una conexión
			Connection conn = Conexion.getInstancia().getConn();

			// definir la query
            PreparedStatement stmt = conn.prepareStatement("select * from plataforma where idplataforma=?");
            
            // setear el/los parámetros
            stmt.setInt(1, id);

            

            // ejecutar query y obtener resultados
            ResultSet rs= stmt.executeQuery();

            // mapear de resultset a objeto
            if(rs.next()) {
        		p=new Plataforma();
                p.setId(rs.getInt("idplataforma"));
                p.setNombre(rs.getString("nombre"));
            }
            //cerrar recursos
            if(rs!=null){rs.close();}
            if(stmt!=null){stmt.close();}

		    conn.close();
		    
		    // mostrar objeto
		    System.out.println("Buscar por id");
		    System.out.println(p);
		    System.out.println();System.out.println();

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return p;
	}
	
	public static void insertarNuevo(String nombre) {
		Plataforma plataforma= new Plataforma();
		
		plataforma.setNombre(nombre);
		
		try {
			Connection conn = Conexion.getInstancia().getConn();
			// definir la query
            PreparedStatement pstmt = conn.prepareStatement(
            		"insert into plataforma(nombre) values (?)"
            		,PreparedStatement.RETURN_GENERATED_KEYS
            		);
            
            
            pstmt.setString(1, plataforma.getNombre());

            pstmt.executeUpdate();
            
            ResultSet keyResultSet=pstmt.getGeneratedKeys();

            if(keyResultSet!=null && keyResultSet.next()) {
                    int id=keyResultSet.getInt(1);
                    System.out.println("ID: "+id);
                    plataforma.setId(id);
            }


            if(keyResultSet!=null){keyResultSet.close();}
            if(pstmt!=null){pstmt.close();}

		    conn.close();
		    
		    // mostrar objeto
		    System.out.println("Nueva Plataforma");
		    System.out.println(plataforma);
		    System.out.println();System.out.println();

		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	

}
