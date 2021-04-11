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

		columna=Consola.leerColumna(jugador);
		finalizar=tablero.introducirFicha(columna, jugador.getColorFichas());

		return finalizar;
	}


	public void jugar() throws OperationNotSupportedException {
		int turno=0;
		boolean hayGanador=false;
		Jugador jugadorQueJuega=jugadores[turno];
		while(!tablero.estaLleno() && !hayGanador) {
			jugadorQueJuega=jugadores[turno++ % NUMERO_JUGADORES];
			hayGanador=tirar(jugadorQueJuega);
		}
		if(hayGanador) {
			System.out.printf("ENHORABUENA, %s has ganado!!!", jugadorQueJuega);
		}else {
			System.out.println("Habéis empatado ya que no quedan más casillas.");
		}
	}


}
