package entities;

public class Propuesta {
	private int id_propuesta;
	private String nombrejuego;
	private String descripcionjuego;
	private String companiajuego;
	private String estado;
	
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

	public String getCompaniajuego() {
		return companiajuego;
	}

	public void setCompaniajuego(String companiajuego) {
		this.companiajuego = companiajuego;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
