package servidor;



import servidor.AbmcUsuario.login;

import servidor.AbmcUsuario.registro;

import servidor.AbmcUsuario.recuperarpersona;

import servidor.AbmcUsuario.verificartoken;

import servidor.AbmcUsuario.cambiarpassword;
import servidor.AbmcUsuario.obtencionfotousuario;


import servidor.Juegos.listajuegos;
import servidor.Empresas.listaempresas;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import servidor.AbmcResenia.obtenerResenias;

public class ServerHTTP {

	public static void main(String[] args) throws IOException {
		
		try {
		HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
		
		server.createContext("/login", new login());
		server.createContext("/registro", new registro());
		server.createContext("/recuperarpassword", new recuperarpersona());
		server.createContext("/verificartoken", new verificartoken());
		server.createContext("/cambiarpassword", new cambiarpassword());
		server.createContext("/allresenias", new obtenerResenias());
		server.createContext("/listajuegos", new listajuegos());
		server.createContext("/listaempresas", new listaempresas());
		server.createContext("/fotousuario", new obtencionfotousuario());
		server.start();
		System.out.println("Server corriendo");
		}
		catch (IOException e){
			 System.out.println("error al inciar el servidor:" + e);
			
		}
				
	}
	
	}	
	
	
	
	
	


