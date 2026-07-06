package ui;
import servidor.AbmcCompania;
import servidor.AbmcGrupo;
import servidor.AbmcPlataforma;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import data.ApiJuego;
import data.ApiPlataforma;
import data.Conexion;
import entities.Plataforma;
import entities.Resenia;
import entities.Compania;
import entities.Grupo;
import entities.Juego;
import servidor.AbmcResenia;


public class Main {

	public static void main(String[] args) {
		LinkedList<Resenia> reseñas = (LinkedList<Resenia>) AbmcResenia.recuperarTodos();
		for (Resenia r : reseñas) {
		    System.out.println(r);
		}
		
	
	}
}
