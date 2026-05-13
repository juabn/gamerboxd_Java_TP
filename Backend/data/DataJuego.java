package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import entities.Compania;
import entities.Juego;
import entities.Plataforma;


public class DataJuego {

	/*
	public void guardarJuegoCompleto(Juego juego) {
	   // Connection conn = ConnectionManager.getConnection(); 
	    
	    try {
	        
	        for (Plataforma p : juego.getPlataformas()) {
	        	DataPlataforma dp = new DataPlataforma();
	            dp.persistirPlataforma(p, conn);
	        }
	        for (Compania c : juego.getCompanias()) {
	        	DataCompania dc = new DataCompania();
	            dc.persistirCompania(c, conn);
	        }



	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}*/
}
