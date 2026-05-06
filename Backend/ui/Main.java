package ui;

import data.ApiJuego;
import data.ApiPlataforma;
import entities.Plataforma;
import entities.Juego;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		//ApiJuego api = new ApiJuego();
		ApiPlataforma apip = new ApiPlataforma();
		System.out.println("seeding");
		ArrayList<Plataforma> plataformas = apip.obtenerPlataformaRAWG();
	//	ArrayList<Juego> juegos = api.obtenerJuegosRAWG();
		
		/*if(juegos!=null) {
			for (Juego j : juegos) {
				System.out.println(j.getInfoJuego());
			}*/
			
			for(Plataforma p : plataformas) {
				System.out.println(p.getInfoPlataforma());
		}
	
	
	}

}
