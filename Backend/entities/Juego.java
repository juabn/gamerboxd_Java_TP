package entities;

import com.google.gson.annotations.SerializedName;
import java.util.stream.Collectors;
import java.util.ArrayList;
public class Juego {
	@SerializedName("id")
	private String id_juego;
	@SerializedName("name")
	private String titulo;
	@SerializedName("background_image")
	private String imagen;
	@SerializedName("description_raw")
	private String descripcion;
	
	private float precio;
	private double puntaje_promedio;
	private String genero;
	
	@SerializedName("platforms")
	private ArrayList<Plataforma> plataformas;
	@SerializedName("developers")
	private String companias;
	
	
	/*public static class DeveloperEntry {
	    private Compania compania; 
	    public Compania getCompania() { return compania; }
	}*/
	
	

	public String getId_juego() {
		return id_juego;
	}
	public void setId_juego(String id_juego) {
		this.id_juego = id_juego;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	

	public String getCompanias() {
		return companias;
	}
	public void setCompanias(String companias) {
		this.companias = companias;
	}
	public double getPuntaje_promedio() {
		return puntaje_promedio;
	}
	public void setPuntaje_promedio(double puntaje_promedio) {
		this.puntaje_promedio = puntaje_promedio;
	}
	//no creo usar estos, 
	public ArrayList<Plataforma> getPlataformas() {return plataformas;}
	
	public void setPlataformas(ArrayList<Plataforma> plataformas) {this.plataformas = plataformas;}
	
	public String getPlataformasTexto() {
	    if (plataformas == null) return "N/A";
	    return plataformas.stream().map(p-> String.valueOf(p.getNombre())).collect(Collectors.joining(", "));
	}
	
	
	

	

	
}