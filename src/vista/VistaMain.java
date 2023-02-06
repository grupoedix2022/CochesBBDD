package vista;

import java.util.*;

import java.io.*;
import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.negocio.Controlador;
public class VistaMain {
			
	public static void main(String[] args) {
		
		Controlador control = new Controlador();
		Scanner scan = new Scanner(System.in); 
		
		//INICIO MENÚ					
			boolean continuar = true;
			
			while (continuar) {								
				Menu.menu1();
				scan = new Scanner(System.in);
				int instruction = Integer.parseInt(scan.nextLine());
				switch(instruction) {
				//Opción 1: añadir				
				case 1:
					
					Coche coche = ingresarCoche(scan);
					if (control.controlMatricula(coche.getMatricula())) {
						
						int altaCoche = control.altaCoche(coche);
						controlErroresCoche(altaCoche);
						
					}else {
						System.out.println("Matrícula repetida");
					}
					break;
					
				//Opción 2: Borrar				
				case 2:
					System.out.println("Introduzca ID para borrar:");
					int id = Integer.parseInt(scan.nextLine());
					if(control.bajaCoche(id)) {
						System.out.println("Borrado correcto");
					}else {
						System.out.println("Error ID no encontrado");
					}					
					break;

				//Opción 3: Consultar				
				case 3:
					System.out.println("Introduzca ID para consultar:");
					id = Integer.parseInt(scan.nextLine());
					System.out.println(control.consultaCoche(id));					
					break;
					
				//Opción 4: Modificar				
				case 4:
					System.out.println("Introduzca ID");
					id = Integer.parseInt(scan.nextLine());
					String res;
					do{
						System.out.println(control.consultaCoche(id));
						System.out.println("¿Este es el coche que quiere modificar? S/N");					
						res = scan.nextLine();					
					}while((!res.equalsIgnoreCase("S")) && (!res.equalsIgnoreCase("N")));
					if (res.equalsIgnoreCase("S")) {
						
						coche = ingresarCoche(scan);
						
						if (control.controlMatricula(coche.getMatricula())) {						
							int modificarCoche = control.modificarCoche(coche);
							controlErroresCoche(modificarCoche);
							
						}else {
							System.out.println("Matrícula repetida");
						}
					}
					break;
					
				//Opción 5: Listar				
				case 5:	
					System.out.println(control.listar());									
					break;
				//Opción 6: Menú Pasajeros
				case 6:
					boolean continuar2 =true;
					while (continuar2) {
						Menu.menu2();
						instruction = Integer.parseInt(scan.nextLine());
						switch(instruction) {
						//"1. Añadir nuevo pasajero."
						case 1:
							System.out.println("Introduzca nombre:");
							String nombre = scan.nextLine();
							System.out.println("Introduzca apellidos:");
							String apellidos = scan.nextLine();
							System.out.println("Introduzca edad:");
							int edad = Integer.parseInt(scan.nextLine());
							System.out.println("Introduzca peso:");
							double peso = Double.parseDouble(scan.nextLine());
							
							Pasajero pasajero = new Pasajero(nombre, apellidos, edad, peso);														
							int altaPasajero = control.altaPasajero(pasajero);
							controlErroresPasajero(altaPasajero);												
							break;
						//2. Borrar pasajero por id.						
						case 2:
							System.out.println("Introduzca ID para borrar:");
							id = Integer.parseInt(scan.nextLine());
							if(control.bajaPasajero(id)) {
								System.out.println("Borrado correcto");
							}else {
								System.out.println("Error ID no encontrado");
							}
							break;
						//Opción 3: Consultar						
						case 3:
							System.out.println("Introduzca ID para consultar:");
							id = Integer.parseInt(scan.nextLine());
							System.out.println(control.consultaPasajero(id));					
							break;
						//Opción 4: "4. Listado de pasajeros
						case 4:					
							System.out.println(control.listarPasajeros());									
							break;
						//Opción 5: Añadir pasajeros a coche.						
						case 5:	
							System.out.println("Introduzca ID Persona");
							id = Integer.parseInt(scan.nextLine());							
							do{
								System.out.println(control.consultaPasajero(id));
								System.out.println("¿Este es el pasajero correcto? S/N");					
								res = scan.nextLine();					
							}while((!res.equalsIgnoreCase("S")) && (!res.equalsIgnoreCase("N")));
							if (res.equalsIgnoreCase("S")) {
								pasajero = control.consultaPasajero(id);
							}else {
								break;
							}
							
							System.out.println("Introduzca ID Coche");
							id = Integer.parseInt(scan.nextLine());							
							do{
								System.out.println(control.consultaCoche(id));
								System.out.println("¿Este es el coche correcto? S/N");					
								res = scan.nextLine();					
							}while((!res.equalsIgnoreCase("S")) && (!res.equalsIgnoreCase("N")));
							if (res.equalsIgnoreCase("S")) {
								coche = control.consultaCoche(id);
							}else {
								break;
							}
							if (pasajero == null || coche == null) {
								System.out.println("Error ID No encontrado");
							}else {
								control.montarPasajero(pasajero, coche);
							}							
							break;																																			
												
						//6. Eliminar pasajero de un coche.	
						case 6:
							System.out.println("Introduzca ID Persona");
							id = Integer.parseInt(scan.nextLine());							
							do{
								System.out.println(control.consultaPasajero(id));
								System.out.println("¿Este es el pasajero correcto? S/N");					
								res = scan.nextLine();					
							}while((!res.equalsIgnoreCase("S")) && (!res.equalsIgnoreCase("N")));
							if (res.equalsIgnoreCase("S")) {
								pasajero = control.consultaPasajero(id);
								System.out.println(control.desmontarPasajero(pasajero));
							}else {
								break;
							}											
							break;
						//7. Listar pasajeros de un coche.
						case 7:
							System.out.println("Introduzca ID Coche");
							id = Integer.parseInt(scan.nextLine());
							List<Pasajero> listaPasajeros = control.listarPasajerosID(id);
							if (listaPasajeros.isEmpty()) {
								System.out.println("Coche sin ocupantes o ID erróneo");
							}else {
								System.out.println(listaPasajeros);
							}							
							break;						
						case 0:
							continuar2=false;
							break;
						default:
							System.out.println("No válido");
							break;
						}
					}
					break;
				case 0:
					continuar=false;
					break;
				default:
					System.out.println("No válido");
					break;
				}
			}
		scan.close();
}
					
			

	

	public static void controlErroresCoche(int i) {
		switch (i) {
		case 0: 
			System.out.println("Ingreso OK");
			break;
		case 1: 
			System.out.println("Error BBDD");
			break;
		case 2: 
			System.out.println("Matrícula errónea");
			break;
			
		}
	}
	
	public static void controlErroresPasajero(int i) {
		switch (i) {
		case 0: 
			System.out.println("Ingreso OK");
			break;
		case 1: 
			System.out.println("Error BBDD");
			break;
		case 2: 
			System.out.println("Pasajero errónea");
			break;
			
		}
	}
	
	public static Coche ingresarCoche(Scanner scan) {
		
		System.out.println("Introduzca matricula:");
		String matricula = scan.nextLine();
		System.out.println("Introduzca marca:");
		String marca = scan.nextLine();
		System.out.println("Introduzca modelo:");
		String modelo = scan.nextLine();
		System.out.println("Introduzca color:");
		String color = scan.nextLine();
		
		Coche coche = new Coche(matricula, marca, modelo,color);
		
		return coche;
		
	}
		
}

	
	

