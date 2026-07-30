package entities;

import java.util.ArrayList;

public class Propuesta {
	private int id_propuesta;
	private String nombrejuego;
	private String descripcionjuego;
	private ArrayList<Compania> companiasJuego;
	private String estado;
	private String foto;
	private String mail_usuario;
	
	

	
	public ArrayList<Compania> getCompaniasJuego() {
		return companiasJuego;
	}

	public void setCompaniasJuego(ArrayList<Compania> companiasJuego) {
		this.companiasJuego = companiasJuego;
	}

	public String getMail_usuario() {
		return mail_usuario;
	}

	public void setMail_usuario(String mail_usuario) {
		this.mail_usuario = mail_usuario;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getIdPropuesta() {
		return id_propuesta;
	}
	
	public void setIdPropuesta(int id_propuesta) {
		this.id_propuesta = id_propuesta;
	}
	
	public String getNombreJuego() {
		return nombrejuego;
	}
	
	public void setNombreJuego(String nombrejuego) {
		this.nombrejuego = nombrejuego;
	}

	public String getDescripcionjuego() {
		return descripcionjuego;
	}

	public void setDescripcionjuego(String descripcionjuego) {
		this.descripcionjuego = descripcionjuego;
	}



	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}