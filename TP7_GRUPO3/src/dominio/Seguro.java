package dominio;

public class Seguro {
	
	int id;
	String descripcion;
	TipoSeguro tipo;
	float costoContratacion;
	float costoAsegurado;
	
	private static int cont = 0;
		
	public Seguro(){}

	public Seguro(String descripcion, TipoSeguro tipo, float costoContratacion, float costoAsegurado) {
		this.id = cont++;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.costoContratacion = costoContratacion;
		this.costoAsegurado = costoAsegurado;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public static int devuelveProximoID() {
		return cont++;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoSeguro getTipo() {
		return tipo;
	}

	public void setTipo(TipoSeguro tipo) {
		this.tipo = tipo;
	}

	public float getCostoContratacion() {
		return costoContratacion;
	}

	public void setCostoContratacion(float costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public float getCostoAsegurado() {
		return costoAsegurado;
	}

	public void setCostoAsegurado(float costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}

	@Override
	public String toString() {
		return "Seguro " + id + ", \n Descripcion: " + descripcion + ".\n Tipo: " + tipo + ". \n Costo Contratacion: "
				+ costoContratacion + ". \n Costo Asegurado: " + costoAsegurado;
	}
	
	
	
	

}
