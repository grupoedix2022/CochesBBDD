package modelo.persistencia.interfaces;
import modelo.entidad.*;
import java.util.*;

public interface DaoPasajero {
	/**
	 * Metodo que da alta Pasajero en la BBDD.
	 * @param p Pasajero para dar de alta.
	 * @return true en caso de que se haya dado de alta. false en caso de error
	 * con la BBDD.
	 */
	public boolean altaPasajero(Pasajero p);
	
	/**
	 * Metodo que da baja Pasajero en la BBDD. 
	 * @param id del Pasajero que se necesita eliminar
	 * @return true en caso de que se haya dado de baja. false en caso de error
	 * con la BBDD.
	 */
	public boolean bajaPasajero(int id);
	
	/**
	 * Metodo consultar Pasajero en la BBDD.
	 * @param id del Pasajero que necesitamos consultar.
	 * @return Pasajero que se ha pedido.
	 */
	public Pasajero consultarPasajero(int id);
	
	/**
	 * Añadir pasajero a coche
	 * @param p Pasajero al que montar en coche
	 * @param c Coche en el que se sube pasajero
	 * @return true en caso de que se haya dado de baja. false en caso de error
	 * con la BBDD.
	 */
	public boolean montarPasajero(Pasajero p, Coche c);
	
	/**
	 * Eliminar coche pasajero (pone en null)
	 * @param p Pasajero al que desmontar en coche
	 * @return true en caso de que se haya dado de baja. false en caso de error
	 * con la BBDD.
	 */
	public boolean desmontarPasajero(Pasajero p);
	
	
	/**
	 * Método que modifica los datos de un Pasajero tras un objeto dado.
	 * @param p Pasajero que reemplazará al existente
	 * @return true si se ha modificado con éxito. false si hubo error en BBDD.
	 */
	public boolean modificarPasajero(Pasajero p);
	
	/**
	 * Devuelve listado de Pasajeros de la BBDD
	 * @return Lista de Pasajeros
	 */
	public List<Pasajero> listarPasajeros();
	
	/**
	 * Método que lista a todos los pasajeros
	 * @param id Coche de los pasajeros
	 * @return Lista de todos los pasajeros del coche.
	 */
	public List<Pasajero> listarPasajeros(int id);
	
}
