package ui;

import java.util.ArrayList;

import data.ApiJuego;
import data.DataJuego;
import entities.Juego;

public class Main {
	public static void seed() {
		ApiJuego api = new ApiJuego();
	    DataJuego dj = new DataJuego();

	    System.out.println("seeding..");
	    
	    // 1. Traemos la lista de la API (Asegúrate de que page_size sea 100 en ApiJuego)
	    ArrayList<Juego> listaJuegos = api.obtenerJuegosRAWG();

	    if (listaJuegos != null) {
	        for (Juego j : listaJuegos) {
	            // 2. Procesamos cada juego uno por uno
	            dj.registrarJuegoCompleto(j);
	        }
	        System.out.println("--- Proceso de seeding finalizado ---");
	    }
	}
	
	public static void main(String[] args) {
	   
		seed();
	}
		
	
	
	

}
