package modelo.negocio;

import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.DaoPasajeroMySql;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
public class Controlador {
	
	DaoCocheMySql daoCoche = new DaoCocheMySql();
	DaoPasajeroMySql daoPasajero = new DaoPasajeroMySql();
	
	
	//COCHE
	/**
	 * Método dar alta vehículo
	 * @param c Coche para dar de alta
	 * @return 0 ok; 1 error en BBDD; 2 matrícula errónea
	 */
	public int altaCoche(Coche c){
		if(c.getMatricula().length() <= 7 
				&& c.getMatricula().matches("^\\d{4}?[ -]*([A-Z]{3})$")) {
			boolean alta = daoCoche.altaCoche(c);
			if(alta) {
				return 0;
			}else {
				return 1;
			}
		}else {
			return 2;
		}
	}
	
	public boolean bajaCoche(int i) {
		boolean baja = daoCoche.bajaCoche(i);
		return baja;
	}

	public Coche consultaCoche(int i) {
		Coche c = daoCoche.consultarCoche(i);
		return c;
	}
	
	public int modificarCoche(Coche c){
		if(c.getMatricula().length() <= 7 
				&& c.getMatricula().matches("^\\d{4}?[ -]*([A-Z]{3})$")) {
			boolean alta = daoCoche.modificarCoche(c);
			if(alta) {
				return 0;
			}else {
				return 1;
			}
		}else {
			return 2;
		}
	}
	
	public List<Coche> listar(){
		return daoCoche.listarCoches();
	}
	
	//PASAJERO
	public int altaPasajero(Pasajero p){
		if(p.getNombre().length() <= 20
				&& p.getNombre().length() <= 20) {
			boolean alta = daoPasajero.altaPasajero(p);
			if(alta) {
				return 0;
			}else {
				return 1;
			}
		}else {
			return 2;
		}
	}
	
	public boolean bajaPasajero(int i) {
		boolean baja = daoPasajero.bajaPasajero(i);
		return baja;
	}
	
	public Pasajero consultaPasajero(int i) {
		Pasajero p = daoPasajero.consultarPasajero(i);
		return p;
	}
	
	public int modificarPasajero(Pasajero p){
		if(p.getNombre().length() <= 20
				&& p.getNombre().length() <= 20) {
			boolean alta = daoPasajero.modificarPasajero(p);
			if(alta) {
				return 0;
			}else {
				return 1;
			}
		}else {
			return 2;
		}
	}
	
	public boolean montarPasajero(Pasajero p, Coche c){
		boolean alta = false;
		if (listarPasajerosID(c.getId()).size() < 4) {
			alta = daoPasajero.montarPasajero(p, c);
			return alta;
		}else {
			return alta;
		}
	}
	
	public boolean desmontarPasajero(Pasajero p) {
		boolean baja = daoPasajero.desmontarPasajero(p);
		return baja;
	}
	
	public List<Pasajero> listarPasajeros(){
		return daoPasajero.listarPasajeros();
	}
	
	public List<Pasajero> listarPasajerosID(int id){
		return daoPasajero.listarPasajeros(id);
	}
	
	public boolean controlMatricula(String matricula) {
		
		List<Coche> lista = this.listar();
		for (Coche c: lista) {
			if (c.getMatricula().equalsIgnoreCase(matricula)){
				return false;
			}
		}
		return true;
	}
	
}
