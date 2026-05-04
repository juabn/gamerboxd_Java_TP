package ui;

import data.ApiJuego;
import entities.Juego;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ApiJuego api = new ApiJuego();
		System.out.println("seeding");
		ArrayList<Juego> juegos = api.obtenerJuegos();
		
		if (juegos!=null) {
			for (Juego j : juegos) {
		        System.out.println("========================================");
		        System.out.println("ID: " + j.getId_juego());
		        System.out.println("TÍTULO: " + j.getTitulo());
		        System.out.println("DESC/SLUG: " + j.getDescripcion());
		        System.out.println("IMAGEN: " + j.getImagen());
		        System.out.println("========================================");
		    }
		}else {
		    System.out.println("La lista de juegos está vacía. Revisá el split en ApiJuego.");
		}
		
	}

}
