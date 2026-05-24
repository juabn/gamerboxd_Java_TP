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
		
		
		LinkedList<Compania> companias = AbmcCompania.recuperarTodos();
		System.out.println("Listado Completo");
		// Recorremos la lista objeto por objeto
		for (Compania c : companias) {
		    System.out.println("ID: " + c.getId() + " - Nombre: " + c.getNombre()); 
		    // Ajustá 'getId()' y 'getNombre()' según los nombres reales de tus getters
		}
	
	
	}
}
