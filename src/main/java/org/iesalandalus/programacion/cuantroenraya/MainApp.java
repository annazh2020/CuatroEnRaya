package org.iesalandalus.programacion.cuantroenraya;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.cuantroenraya.vista.Consola;
import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;

public class MainApp {

	public static void main(String[] args) throws OperationNotSupportedException {

		System.out.println("Introduce los datos del PRIMER jugador.");

		String nombre1= Consola.leerNombre();
		Ficha ficha1 = Consola.elegirColorFichas();

		Jugador jugador1= new Jugador (nombre1, ficha1);

		System.out.println("Introduce los datos del SEGUNDO jugador.");

		String nombre2= Consola.leerNombre();
		Ficha ficha2;

		if (jugador1.getColorFichas().equals(Ficha.AZUL)) {
			ficha2=Ficha.VERDE;
		}else {
			ficha2=Ficha.AZUL;
		}

		Jugador jugador2= new Jugador (nombre2, ficha2);

		CuatroEnRaya juego = new CuatroEnRaya (jugador1,jugador2);

		juego.jugar();


	}

}
