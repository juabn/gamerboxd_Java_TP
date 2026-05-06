package entities;

import com.google.gson.annotations.SerializedName;

public class Plataforma {
	private int id;
	@SerializedName("name")
	private String nombre;
	@SerializedName("image")
	private String imagen;
    public String getNombre() { return nombre; }
    public void setNombre (String nombre) {this.nombre = nombre;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getImagen() {return imagen;}
    public void setImagen(String imagen) {this.imagen = imagen;}
    
    public String getInfoPlataforma(){
    	return "ID: "+this.id+"\nNOMBRE: "+this.nombre+"\nIMAGEN: "+this.imagen;
    }
}
