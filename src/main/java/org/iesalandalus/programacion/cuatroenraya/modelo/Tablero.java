package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Tablero {

	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

	private Casilla[][] casillas;

	public Tablero() {
		casillas = new Casilla[FILAS][COLUMNAS];

		// Inicializamos el tablero con las casillas (con las fichas a null)
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}


	//la columna está vacía cuando la última casilla (la de abajo) de esta columna está vacía

	private boolean columnaVacia(int columna) {
		return !casillas[0][columna].estaOcupada();
	}

	//el tablero está vacío cuando todas las columnas están vacías.

	public boolean estaVacio() {

		for (int i = 0; i < COLUMNAS; i++) {
			if(!columnaVacia(i)) {
				return false;
			}	
		}
		return true;
	}




	// Una columna está llena cuando la última ficha (de la última fila la de arriba) está ocupada.

	private boolean columnaLlena(int columna) {
		return casillas[FILAS-1][columna].estaOcupada();
	}

	public boolean estaLleno() {
		for (int j = 0; j < COLUMNAS; j++) {
			if (!columnaLlena(j)){
				return false;
			}
		}
		return true;
	}

	private void comprobarFicha (Ficha ficha) {
		if (ficha==null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}

	}


	private void comprobarColumna (int columna) throws OperationNotSupportedException {
		if ((columna>=COLUMNAS)||(columna<0)) {
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		}
		if (columnaLlena(columna)) {
			throw new OperationNotSupportedException("ERROR: Columna llena.");
		}

	}


	private int getPrimeraFilaVacia(int columna) {
		int fila;
		boolean seguir=true;
		for (fila=0; fila<FILAS && seguir; fila++) {
			seguir=casillas[fila][columna].estaOcupada();
		}
		return fila-1;
	}

	private boolean objetivoAlcanzado(int fichasConsec) {
		return (fichasConsec>=FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS);
	}


	private boolean comprobarHorizontal(int fila, Ficha ficha) {
		boolean hayConsecutivas=false;
		int numeroFichas=0;
		for (int j=0; j < COLUMNAS && !hayConsecutivas; j++) {
			Casilla casilla=casillas[fila][j];
			Ficha fichaTablero=casilla.getFicha();
			if (ficha.equals(fichaTablero)) {
				numeroFichas++;
				hayConsecutivas=objetivoAlcanzado(numeroFichas);
			}else {
				numeroFichas=0;
			}
		}
		return hayConsecutivas;

	}

	private boolean comprobarVertical (int columna, Ficha ficha) {
		boolean hayConsecutivas=false;
		int numeroFichas=0;
		for (int i=0; i<FILAS && !hayConsecutivas; i++) {
			Casilla casilla=casillas[i][columna];
			Ficha fichaTablero=casilla.getFicha();
			if(ficha.equals(fichaTablero)) {
				numeroFichas++;
				hayConsecutivas=objetivoAlcanzado(numeroFichas);
			}else {
				numeroFichas=0;
			}
		}
		return hayConsecutivas;
	}

	private int menor (int fila,int columna) {
		return (fila<columna)? fila:columna;
	}


	
	private boolean comprobarDiagonalINE(int fila, int columna, Ficha ficha) {
		int desplazInicial=menor(fila,columna);
		int filaInicial=fila-desplazInicial;
		int columnaInicial=columna-desplazInicial;

		int fichasIgualesConsecutivas=0;

		for(int i=filaInicial,j=columnaInicial;!objetivoAlcanzado(fichasIgualesConsecutivas)&& (i<FILAS && j<COLUMNAS); i++, j++) {
			if (casillas[i][j].estaOcupada()&&casillas[i][j].getFicha().equals(ficha)) {
				fichasIgualesConsecutivas++;
			
			}else {
				fichasIgualesConsecutivas=0;
			}
		}

		return objetivoAlcanzado(fichasIgualesConsecutivas);
	}
	

	
	private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
		int desplazInicial=menor(fila,COLUMNAS-1-columna);
		int filaInicial=fila-desplazInicial;
		int columnaInicial=columna+desplazInicial;

		int fichasIgualesConsecutivas=0;

		for(int i=filaInicial,j=columnaInicial;!objetivoAlcanzado(fichasIgualesConsecutivas)&& (i<FILAS && j>=0); i++, j--) {
			if (casillas[i][j].estaOcupada()&&casillas[i][j].getFicha().equals(ficha)) {
				fichasIgualesConsecutivas++;
			
			}else {
				fichasIgualesConsecutivas=0;
			}
		}

		return objetivoAlcanzado(fichasIgualesConsecutivas);
	}

	private boolean comprobarTirada(int fila, int columna, Ficha ficha) {
		return comprobarHorizontal(fila,ficha) || comprobarVertical(columna,ficha) || comprobarDiagonalINE(fila,columna,ficha) || comprobarDiagonalNO(fila,columna,ficha);
		
	}


	public boolean introducirFicha (int columna, Ficha ficha) throws OperationNotSupportedException {
		comprobarFicha(ficha);
		comprobarColumna(columna);
		boolean tirada=false;
		int filaLibre=0;
		
		//si la columna no está llena, al menos hay una posición libre
		if(!columnaLlena(columna)) {
			
			//buscar la primera fila que esté libre
			filaLibre=getPrimeraFilaVacia(columna);
			Casilla casilla = casillas[filaLibre][columna];
			
			//si la casilla está libre
			if (!casilla.estaOcupada()) {
				casilla.setFicha(ficha);
				casillas[filaLibre][columna]=casilla;
				//System.out.println(toString());
				tirada=comprobarTirada(filaLibre,columna,ficha);
			}

		}else {
			throw new OperationNotSupportedException("ERROR: Columna llena.");
		}
		return tirada;
	}

	@Override
	
	public String toString() {
		final String saltoLineaVertical = "\n";
		final String separacionHorizontal = " ";
		final StringBuilder sb = new StringBuilder();

		if (casillas != null) {

			for (int i = FILAS - 1; i >= 0; i--) {
				final Casilla[] casilla = casillas[i];

				if (casilla != null)
					for (int j = 0; j < COLUMNAS; j++) {
						final Casilla casilla1 = casilla[j];

						if (j == 0) {
							sb.append('|');
						}

						sb.append(casilla1);

						if (j == COLUMNAS - 1) {
							sb.append('|');
						}
					}
				if (i >= 0) {
					sb.append(saltoLineaVertical);
				}

			}

			sb.append(separacionHorizontal);
			for (int i = 0; i < COLUMNAS; i++) {
				sb.append("-");
			}
			sb.append(saltoLineaVertical);
		}

		return sb.toString();
	}







}


