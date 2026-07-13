package entities;

import com.google.gson.annotations.SerializedName;

public class Compania {
	
	private int id;
	@SerializedName("name")
	private String nombre;

	private String estado;

	
	

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	} 
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	
}