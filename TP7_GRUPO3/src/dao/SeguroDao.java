package dao;

import java.util.List;

import dominio.Seguro;



public interface SeguroDao {

	public boolean insert(Seguro seguro);
	public List<Seguro> readAll();
	public List<Seguro> filtrarSegunTipo(int idTipo);
}
	
	

