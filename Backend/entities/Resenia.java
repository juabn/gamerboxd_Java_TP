package entities;

public class Resenia {
	int id_juego;
	String fecha;
	String hora;
	String titulo;
	String descripcion;
	float puntaje;
	String mail_usuario;
	private Juego juego;
    private Persona usuario;
    
    public Juego getJuego() { return juego; }
    public void setJuego(Juego juego) { this.juego = juego; }
    
    public Persona getUsuario() { return usuario; }
    public void setUsuario(Persona usuario) { this.usuario = usuario; }
	
	public int getId_juego() {
		return id_juego;
	}
	public void setId_juego(int id_juego) {
		this.id_juego = id_juego;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(float puntaje) {
		this.puntaje = puntaje;
	}
	public String getMail_usuario() {
		return mail_usuario;
	}
	public void setMail_usuario(String mail_usuario) {
		this.mail_usuario = mail_usuario;
	}
	
	public String toString() {
	    return "Resenia [id_juego=" + id_juego + ", titulo=" + titulo + ", puntaje=" + puntaje + ", mail_usuario=" + mail_usuario + "]";
	}
	
	
}
