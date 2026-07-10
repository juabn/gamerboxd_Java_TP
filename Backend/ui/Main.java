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

import servidor.GeneracionWebToken;

import servidor.AbmcResenia;

import servidor.Juegos;



public class Main {

	public static void main(String[] args) {
		
		String valor;
		
		valor = GeneracionWebToken.enviotoken("santiagomalet", "sdss");
		System.out.println(valor);
		
		
	}


}
