package ui;
import servidor.AbmcCompania;

import java.util.LinkedList;

import data.ApiJuego;
import data.ApiPlataforma;
import data.Conexion;
import entities.Plataforma;
import entities.Compania;
import entities.Juego;



public class Main {

	public static void main(String[] args) {
		
		
		//LinkedList<Compania> companias = AbmcCompania.recuperarTodos();
		Compania compania = AbmcCompania.recuperarPorId(10);
		System.out.println("ID: " + compania.getId() + ", Nombre: "+compania.getNombre());
	
	
	}
}
