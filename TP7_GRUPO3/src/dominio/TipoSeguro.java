package dominio;

public class TipoSeguro {
	int id;
	String descripcion;
	
	public TipoSeguro() {}
	
	public TipoSeguro(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public TipoSeguro(int id) {
        this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoSeguro " + id + ", Descripcion: " + descripcion;
	}
	
	
	
}
