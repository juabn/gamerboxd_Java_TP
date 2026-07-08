package ui;
import servidor.AbmcCompania;


import servidor.AbmcGrupo;
import servidor.AbmcPlataforma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import data.ApiPlataforma;
import data.Conexion;
import data.DataJuego;
import entities.Plataforma;
import entities.Resenia;
import entities.Compania;
import entities.Grupo;
import entities.Juego;

import servidor.AbmcResenia;

import servidor.Juegos;



public class Main {

	public static void main(String[] args) {

		AbmcResenia.insertarNuevo(28,"juan.perez@gmail.com", "este juego es una poronga atomica", "la verdad que es una mierda este juego honestamente, me aburri todo el tramo me quede dormido y me desperte con la agradable sopresa de que la querida mama de un amigo (se llama laura) me estaba haciendo una felacion por debajo de mi escritorio. Esto fue lo mejor de mi experiencia con el juego este de re mierda", (float) 0.1);;

		
		/*
		ApiJuego apiJuego = new ApiJuego();
		ArrayList<Juego> listaDeJuegos = apiJuego.obtenerJuegosRAWG();
		
		Collections.sort(listaDeJuegos, (j1, j2) -> j1.getTitulo().compareToIgnoreCase(j2.getTitulo()));
		
		for (Juego j : listaDeJuegos) {
	        System.out.println(j.getImagen());
	    }


		
		

		
	*/
		
		
	}


}
