package servidor;



import servidor.AbmcUsuario.login;



import servidor.AbmcUsuario.registro;
import servidor.AbmcUsuario.recuperarpersona;
import servidor.AbmcUsuario.verificartoken;
import servidor.AbmcCompania.actualizarnombrecompania;
import servidor.AbmcCompania.bajalogicacompania;
import servidor.AbmcCompania.crearcompania;
import servidor.AbmcCompania.existeempresa;
import servidor.AbmcCompania.devolverempresa;
import servidor.AbmcUsuario.cambiarpassword;
import servidor.AbmcUsuario.obtencionfotousuario;

import servidor.Juegos.juegoid;

import servidor.Juegos.listajuegos;
import servidor.Empresas.listaempresas;
import servidor.AbmcGrupo.creargrupo;
import servidor.AbmcUsuario.actualizardatosusuario;
import servidor.AbmcUsuario.verificarjwt;
import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import servidor.AbmcResenia.obtenerResenias;
import servidor.AbmcCompania.actualizardatosdeempresa;
import servidor.AbmcUsuario.dardebaja;
import servidor.AbmcUsuario.convertirenadmin;
import servidor.AbmcPropuesta.crearPropuesta;
import servidor.AbmcPropuesta.listarPropuestas;

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
		server.createContext("/actualizardatosperfil", new actualizardatosusuario());
		server.createContext("/verificarjwt", new verificarjwt());

		server.createContext("/juego/", new juegoid());

		server.createContext("/Actualizarnombrecompania", new actualizarnombrecompania());
		server.createContext("/bajalogicacompania", new bajalogicacompania());
		server.createContext("/crearcompania", new crearcompania());
		server.createContext("/existeempresa", new existeempresa());
		server.createContext("/devolverempresa", new devolverempresa());
		server.createContext("/actualizardatosdeempresa", new actualizardatosdeempresa());
		server.createContext("/dardebaja", new dardebaja());

		

		server.createContext("/fotousuario", new obtencionfotousuario());

		server.createContext("/creargrupo", new creargrupo());
		
		server.createContext("/convertirenadmin", new convertirenadmin());
		server.createContext("/crearPropuesta", new crearPropuesta());
		
		
		
		server.createContext("/listarPropuestas", new listarPropuestas());
		
		
		

		server.start();
		System.out.println("Server corriendo");
		}
		catch (IOException e){
			 System.out.println("error al inciar el servidor:" + e);
			
		}
				
	}
	
	}	

