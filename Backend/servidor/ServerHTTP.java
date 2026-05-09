package servidor;


import servidor.AbmcUsuario.login;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


public class ServerHTTP {

	public static void main(String[] args) throws IOException {
		
		try {
		HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
		
		server.createContext("/login", new login());
		server.start();
		System.out.println("Server corriendo");
		}
		catch (IOException e){
			
			System.out.println("error al inciar el servidor:" + e);
			
		}
				
	}
	
	}	
	
	
	
	
	


