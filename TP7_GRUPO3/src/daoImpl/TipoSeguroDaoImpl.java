package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoSeguroDao;
import dominio.TipoSeguro;

public class TipoSeguroDaoImpl implements TipoSeguroDao {
	
	private static final String READ_ALL = "select * from tiposeguros";

	@Override
	public List<TipoSeguro> readAll() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		List<TipoSeguro> ListaTiposSeguro = new ArrayList<TipoSeguro>();
        String sql = READ_ALL;
        
        try (Connection conexion = Conexion.getConnection();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
            	
            	TipoSeguro TSeguro = new TipoSeguro();
                
            	TSeguro.setId(resultSet.getInt("idTipo"));
                TSeguro.setDescripcion(resultSet.getString("descripcion"));                   
                ListaTiposSeguro.add(TSeguro);
                System.out.println(TSeguro.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return ListaTiposSeguro;
		
	}
	
}
