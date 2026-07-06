package servidor;


import servidor.AbmcUsuario.login;

import servidor.AbmcResenia.;
import servidor.AbmcUsuario.registro;

import servidor.AbmcUsuario.recuperarpersona;

import servidor.AbmcUsuario.verificartoken;

import servidor.AbmcUsuario.cambiarpassword;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


public class ServerHTTP {

	public static void main(String[] args) throws IOException {
		
		try {
		HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
		
		server.createContext("/login", new login());
		server.createContext("/registro", new registro());
		server.createContext("/recuperarpassword", new recuperarpersona());
		server.createContext("/verificartoken", new verificartoken());
		server.createContext("/cambiarpassword", new cambiarpassword());
		server.createContext("/api/resenias", new ());
		
		server.start();
		System.out.println("Server corriendo");
		}
		catch (IOException e){
			 System.out.println("error al inciar el servidor:" + e);
			
		}
				
	}
	
	}	
	
	
	
	
	


