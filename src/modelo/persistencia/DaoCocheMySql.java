package modelo.persistencia;
import java.util.List;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.*;

public class DaoCocheMySql implements DaoCoche{
	
private Connection conexion;
	
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
	public boolean altaCoche(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "INSERT INTO coches(matricula, marca, modelo, color) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setString(4, c.getColor());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		}catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}	

	@Override
	public boolean bajaCoche(int id) {
		if(!abrirConexion()){
			return false;
		}
		boolean baja = true;
		
		String query = "DELETE FROM coches WHERE id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				baja = false;
			}
		}catch(SQLException e) {
			System.out.println("baja -> Error al eliminar: " + id);
			baja = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return baja;
	}

	@Override
	public Coche consultarCoche(int id) {
		if(!abrirConexion()){
			return null;
		}
		Coche c = null;
		
		String query = "SELECT * FROM coches WHERE id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c = new Coche();
				c.setId(rs.getInt(1));
				c.setMatricula(rs.getString(2));
				c.setMarca(rs.getString(3));
				c.setModelo(rs.getString(4));
				c.setColor(rs.getString(5));
				
			}
		}catch(SQLException e) {
			System.out.println("Consultar -> Error al obtener: " + id);			
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return c;
	}

	@Override
	public boolean modificarCoche(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificar = true;
		String query = "UPDATE SET matricula=?, marca=?, modelo=?, color=? WHERE id=?";
				
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setString(4, c.getColor());
			ps.setInt(5, c.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificar = false;
			else
				modificar = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar" + c);
			modificar = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificar;
	}
	

	@Override
	public List<Coche> listarCoches() {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select * from coches";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
						
			while(rs.next()) {
				Coche c = new Coche();
				c.setId(rs.getInt(1));
				c.setMatricula(rs.getString(2));
				c.setMarca(rs.getString(3));
				c.setModelo(rs.getString(4));
				c.setColor(rs.getString(5));
				listaCoches.add(c);				
			}
		}catch(SQLException e) {
			System.out.println("Consultar listado -> Error al obtener listado");			
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaCoches;
	
	
	}
}
	

