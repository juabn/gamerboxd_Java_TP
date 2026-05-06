package entities;

import com.google.gson.annotations.SerializedName;

class Compania {
	
	private int id;
	@SerializedName("name")
	private String nombre;
	@SerializedName("description")
	private String descripcion;
	
	
	public int getId_comp() {
		return id;
	}
	public void setId_comp(int id_comp) {
		this.id = id_comp;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
}