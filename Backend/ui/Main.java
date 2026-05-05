package ui;

import data.ApiJuego;
import entities.Juego;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ApiJuego api = new ApiJuego();
		System.out.println("seeding");
		ArrayList<Juego> juegos = api.obtenerJuegos();
		
		if(juegos!=null) {
			for (Juego j : juegos) {
				System.out.println(j.getInfoJuego());
			}
		}
	}

}
