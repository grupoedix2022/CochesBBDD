package modelo.entidad;

public class Pasajero {
	
	private int id;
	private String nombre;
	private String apellidos;
	private int edad;
	private double peso;
	private Coche coche;
	
	public Pasajero(int id, String nombre, String apellidos, int edad, double peso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.peso = peso;
		this.coche = null;
	}
			
	
	public Pasajero(String nombre, String apellidos, int edad, double peso) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.peso = peso;
	}


	public Pasajero() {
		super();
	}



	public Coche getCoche() {
		return coche;
	}


	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}


	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", peso="
				+ peso + ", coche=" + coche + "]";
	}
	
	
	
	

}
