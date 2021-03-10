package org.iesalandalus.programacion.cuantroenraya;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.cuantroenraya.vista.Consola;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;

public class CuatroEnRaya {
	
	static final int NUMERO_JUGADORES= 2;
	private Tablero tablero;
	
	private Jugador[] jugadores;
	
	public CuatroEnRaya (Jugador jugador1, Jugador jugador2) {
		
		jugadores=new Jugador[NUMERO_JUGADORES];
		
		if (jugador1!=null && jugador2!=null) {
			jugadores[0]=jugador1;
			jugadores[1]=jugador2;
		}else {
			throw new NullPointerException("ERROR: los jugadores no pueden tener valor nulo.");
		}
		tablero=new Tablero();
	}
	
	private boolean tirar(Jugador jugador) throws OperationNotSupportedException {
		int columna;
		boolean finalizar =false;
		
		do { 
			columna=Consola.leerColumna(jugador);
			finalizar=tablero.introducirFicha(columna, jugador.getColorFichas());
		} while (!finalizar);
		
		return true;
	}
	
	public void jugar() {
		 
		
		//System.out.println("ENHORABUENA" +jugadores.toString()+ " has ganado!");
	}
		
		
		
	

}
