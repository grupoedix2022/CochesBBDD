package modelo.persistencia.interfaces;

import modelo.entidad.*;
import java.util.*;

public interface DaoCoche {
	
	/**
	 * Metodo que da alta coche en la BBDD.
	 * @param c coche para dar de alta.
	 * @return true en caso de que se haya dado de alta. false en caso de error
	 * con la BBDD.
	 */
	public boolean altaCoche(Coche c);
	
	/**
	 * Metodo que da baja coche en la BBDD. 
	 * @param id del coche que se necesita eliminar
	 * @return true en caso de que se haya dado de baja. false en caso de error
	 * con la BBDD.
	 */
	public boolean bajaCoche(int id);
	
	/**
	 * Metodo consultar coche en la BBDD.
	 * @param id del coche que necesitamos consultar.
	 * @return Coche que se ha pedido.
	 */
	public Coche consultarCoche(int id);
	
	/**
	 * Método que modifica los datos de un coche tras un objeto dado.
	 * @param c Coche que reemplazará al existente
	 * @return true si se ha modificado con éxito. false si hubo error en BBDD.
	 */
	public boolean modificarCoche(Coche c);
	
	/**
	 * Devuelve listado de Coches de la BBDD
	 * @return Lista de Coches
	 */
	public List<Coche> listarCoches();
	
}
