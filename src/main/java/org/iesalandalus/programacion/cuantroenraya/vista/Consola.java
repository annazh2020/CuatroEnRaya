package org.iesalandalus.programacion.cuantroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	private Consola () {};
	
	public static String leerNombre() {
		String nombre="";
		do {
			System.out.println("Introduce el nombre del jugador");
			nombre=Entrada.cadena();
		}while (nombre.trim().isEmpty());
		
		return nombre;
	}
	
	public static Ficha elegirColorFichas() {
		int eleccion;
		do {
		System.out.println("Elige el color de tus fichas (0-AZUL, 1-VERDE");
		eleccion=Entrada.entero();
		
		}while (eleccion!=0 || eleccion!=1);
		
		if (eleccion==0) {
			return Ficha.AZUL;
		}else
			return Ficha.VERDE;	
	}
	
	public static Jugador leerJugador() {
		String nombre=leerNombre();
		Ficha color=elegirColorFichas();
		Jugador jugador=new Jugador(nombre,color);
		return jugador;
		
	}
	
	public static Jugador leerJugador(Ficha ficha) {
		String nombre=leerNombre();
		Jugador jugador =new Jugador(nombre, ficha);
		return jugador;
	}
	
	public static int leerColumna(Jugador jugador) {
		int columna;
	
		do {
		System.out.println(jugador.getNombre()+",introduce la columna en la que deseas introducir la ficha (0-6):");;
			columna=Entrada.entero();
		} while (columna<0 ||columna>=Tablero.COLUMNAS);
		return columna;
	}
	
	
	

}
