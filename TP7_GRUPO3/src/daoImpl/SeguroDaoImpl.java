package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.SeguroDao;
import dominio.Seguro;
import dominio.TipoSeguro;

public class SeguroDaoImpl implements SeguroDao {
	private static final String insert = "INSERT INTO seguros (descripcion,idTipo,costoContratacion,costoAsegurado) VALUES(?, ?, ?,?)";
	private static final String readall = "select S.idSeguro, S.descripcion, S.costoContratacion, S.costoAsegurado, "
			+ " S.idTipo, TS.descripcion as Descrip from seguros as S "
			+ " inner join tipoSeguros as TS on S.idTipo = TS.idTipo";
	private static final String buscar = "select S.idSeguro, S.descripcion, S.costoContratacion, S.costoAsegurado, "
			+ " S.idTipo, TS.descripcion as Descrip from seguros as S "
			+ " inner join tipoSeguros as TS on S.idTipo = TS.idTipo WHERE S.idTipo  = ?";
	private static final String ultimoId = "SELECT MAX(idSeguro) FROM seguros";

	@Override
	public boolean insert(Seguro seguro) {
		String sql = insert;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(sql)) {

			statement.setString(1, seguro.getDescripcion());
			statement.setString(2, Integer.toString(seguro.getTipo().getId()));
			statement.setString(3, String.valueOf(seguro.getCostoContratacion()));
			statement.setString(4, String.valueOf(seguro.getCostoAsegurado()));

			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int ultimoSeguro() {
		int Id = 0;
		String sql = ultimoId;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				Id = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Id;
	}

	@Override
	public List<Seguro> readAll() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		List<Seguro> seguros = new ArrayList<Seguro>();
		String sql = readall;

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {

				TipoSeguro TSeguro = new TipoSeguro();
				Seguro seguro = new Seguro();

				seguro.setId(resultSet.getInt("idSeguro"));
				seguro.setDescripcion(resultSet.getString("descripcion"));
				seguro.setCostoContratacion(resultSet.getFloat("costoContratacion"));
				seguro.setCostoAsegurado(resultSet.getFloat("costoAsegurado"));

				TSeguro.setId(resultSet.getInt("idTipo"));
				TSeguro.setDescripcion(resultSet.getString("Descrip"));

				seguro.setTipo(TSeguro);
				seguros.add(seguro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return seguros;
	}

	@Override
	public List<Seguro> filtrarSegunTipo(int idTipo) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		List<Seguro> ListSeguros = new ArrayList<Seguro>();
		String sql = buscar;

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(sql)) {

			statement.setInt(1, idTipo);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {

					Seguro seguro = new Seguro();

					seguro.setId(resultSet.getInt("idSeguro"));
					seguro.setDescripcion(resultSet.getString("descripcion"));
					seguro.setCostoContratacion(resultSet.getFloat("costoContratacion"));
					seguro.setCostoAsegurado(resultSet.getFloat("costoAsegurado"));

					TipoSeguro TSeguro = new TipoSeguro();
					TSeguro.setId(resultSet.getInt("idTipo"));
					TSeguro.setDescripcion(resultSet.getString("Descrip"));

					seguro.setTipo(TSeguro);

					ListSeguros.add(seguro);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ListSeguros;
	}

}
