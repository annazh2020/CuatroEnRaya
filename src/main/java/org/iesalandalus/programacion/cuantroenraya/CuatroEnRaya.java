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
		
		//do { 
			columna=Consola.leerColumna(jugador);
			finalizar=tablero.introducirFicha(columna, jugador.getColorFichas());
		//} while (!finalizar);
		
		return finalizar;
	}
	
	public void jugar() throws OperationNotSupportedException {
		 boolean ganador1=false;
		 boolean ganador2=false;
		 while (!tablero.estaLleno() && (!ganador1 && !ganador2)) {
			if (!ganador2) {
				ganador1=tirar(jugadores[0]);
				System.out.println(tablero.toString());
			}
			if (!ganador1) {
				ganador2=tirar(jugadores[1]);
				System.out.println(tablero.toString());
			}
			
		 }
		 if (ganador1) {
			 System.out.println("ENHORABUENA, " +jugadores[0].getNombre()+ " has ganado!!!");
		 }else if (ganador2) {
			 System.out.println("ENHORABUENA, " +jugadores[1].getNombre()+ " has ganado!!!"); 
		 }
		
	}
		
		
		
	

}
