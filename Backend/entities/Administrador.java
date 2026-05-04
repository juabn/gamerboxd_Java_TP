package entities;

class Administrador extends Persona {
	
	private int id;
	private String nombre_usario;
	private int contrasenia;
	private String mail;
	private String foto_perfil;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre_usario() {
		return nombre_usario;
	}
	public void setNombre_usario(String nombre_usario) {
		this.nombre_usario = nombre_usario;
	}
	public int getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(int contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getFoto_perfil() {
		return foto_perfil;
	}
	public void setFoto_perfil(String foto_perfil) {
		this.foto_perfil = foto_perfil;
	}
	
	
	
	
	

}