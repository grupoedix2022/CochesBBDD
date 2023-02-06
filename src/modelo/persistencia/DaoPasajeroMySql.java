package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;
import modelo.persistencia.interfaces.DaoCoche;

public class DaoPasajeroMySql implements DaoPasajero{

	Connection conexion;
	DaoCocheMySql daoCoche = new DaoCocheMySql();
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3307/bbdd";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean altaPasajero(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "INSERT INTO pasajeros(nombre, apellidos, edad, peso) VALUES(?,?,?,?)";
				 
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellidos());
			ps.setInt(3, p.getEdad());
			ps.setDouble(4, p.getPeso());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean bajaPasajero(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean baja = true;
		String query = "DELETE FROM pasajeros WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				baja = false;
		} catch (SQLException e) {
			System.out.println("baja -> No se ha podido dar de baja"
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return baja; 
	}

	@Override
	public Pasajero consultarPasajero(int id) {
		if(!abrirConexion()){
			return null;
		}
		Pasajero p = null;
		
		String query = "SELECT * FROM pasajeros WHERE id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				p = new Pasajero();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setApellidos(rs.getString(3));
				p.setEdad(rs.getInt(4));
				p.setPeso(rs.getDouble(5));
				p.setCoche(daoCoche.consultarCoche(rs.getInt(6)));
				
			}
		}catch(SQLException e) {
			System.out.println("Consultar -> Error al obtener: " + id);			
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return p;
	}	

	@Override
	public boolean modificarPasajero(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificar = true;
		String query = "UPDATE SET nombre=?, apellidos=?, edad=?, peso=? WHERE id=?";
				
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellidos());
			ps.setInt(3, p.getEdad());
			ps.setDouble(4, p.getPeso());
			ps.setInt(5, p.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificar = false;
			else
				modificar = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar" + p);
			modificar = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificar;
	}	

	@Override
	public List<Pasajero> listarPasajeros() {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "select * from pasajeros";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
						
			while(rs.next()) {
				Pasajero p = new Pasajero();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setApellidos(rs.getString(3));
				p.setEdad(rs.getInt(4));
				p.setPeso(rs.getDouble(5));
				p.setCoche(daoCoche.consultarCoche(rs.getInt(6)));
				listaPasajeros.add(p);				
			}
		}catch(SQLException e) {
			System.out.println("Consultar listado -> Error al obtener listado");			
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaPasajeros;
	
	}
	
	@Override
	public boolean montarPasajero(Pasajero p, Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificar = true;
		String query = "UPDATE pasajeros SET cocheid=? WHERE id=?";
				
		try {
			PreparedStatement ps = conexion.prepareStatement(query);			
			ps.setInt(1, c.getId());			
			ps.setInt(2, p.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificar = false;
			else
				modificar = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar" + p);
			modificar = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificar;
	}
	
	@Override
	public boolean desmontarPasajero(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificar = true;
		String query = "UPDATE pasajeros SET cocheid=NULL WHERE id=?";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);			
			ps.setInt(1, p.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificar = false;
			else
				modificar = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar" + p);
			modificar = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificar;
	}

	@Override
	public List<Pasajero> listarPasajeros(int cocheid) {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "SELECT * FROM pasajeros WHERE cocheid=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, cocheid);
			ResultSet rs = ps.executeQuery();
						
			while(rs.next()) {
				Pasajero p = new Pasajero();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setApellidos(rs.getString(3));
				p.setEdad(rs.getInt(4));
				p.setPeso(rs.getDouble(5));
				p.setCoche(daoCoche.consultarCoche(rs.getInt(6)));
				listaPasajeros.add(p);				
			}
		}catch(SQLException e) {
			System.out.println("Consultar listado -> Error al obtener listado");			
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaPasajeros;
		
	}

}
