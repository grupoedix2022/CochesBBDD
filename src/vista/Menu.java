package vista;

public abstract class Menu {
	
	public static void menu1(){
		System.out.println("MENU");
		System.out.println("1. Añadir nuevo coche.");
		System.out.println("2. Borrar coche por id.");
		System.out.println("3. Consulta coche por id.");
		System.out.println("4. Modificar coche por id");
		System.out.println("5. Listado de coches.");
		System.out.println("6. Gestionar pasajeros.");
		System.out.println("0. Salir.");	
	}
	
	public static void menu2(){
		System.out.println("MENU PASAJEROS");
		System.out.println("1. Añadir nuevo pasajero.");
		System.out.println("2. Borrar pasajero por id.");
		System.out.println("3. Consulta pasajero por id.");
		System.out.println("4. Listado de pasajeros.");
		System.out.println("5. Añadir pasajero a coche.");
		System.out.println("6. Eliminar pasajero de coche");
		System.out.println("7. Listar pasajeros de un coche.");
		System.out.println("0. Salir.");		
	}

}
