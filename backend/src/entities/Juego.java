package entities;

public class Juego {
	private int id_juego;
	private String titulo;
	private String imagen;
	private String descripcion;
	private float precio;
	private String plataforma;
	private double puntaje_promedio;
	public int getId_juego() {
		return id_juego;
	}
	public void setId_juego(int id_juego) {
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
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public double getPuntaje_promedio() {
		return puntaje_promedio;
	}
	public void setPuntaje_promedio(double puntaje_promedio) {
		this.puntaje_promedio = puntaje_promedio;
	}
	
	
}
