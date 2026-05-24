package ui;
import servidor.AbmcCompania;
import servidor.AbmcGrupo;
import servidor.AbmcPlataforma;

import java.util.ArrayList;
import java.util.LinkedList;

import data.ApiJuego;
import data.ApiPlataforma;
import data.Conexion;
import entities.Plataforma;
import entities.Compania;
import entities.Grupo;
import entities.Juego;



public class Main {

	public static void main(String[] args) {
		
		//ABMC Grupo uso
		
		//AbmcGrupo.insertarNuevo("foto2.jpg", "IGN", "Grupo reconocitdo internacionalmente ");
		//Grupo g = AbmcGrupo.recuperarPorId(1);
		//LinkedList<Grupo> grupos = AbmcGrupo.recuperarTodos();
		ArrayList<Grupo> grupos = AbmcGrupo.recuperarPorNombre("IG");
		for (Grupo g: grupos) {
			System.out.println("Nombre: "+g.getNombre()+", ID: "+g.getId());
		}
		
	
	}
}
