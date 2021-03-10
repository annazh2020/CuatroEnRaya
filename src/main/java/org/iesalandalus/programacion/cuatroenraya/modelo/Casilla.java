package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {

	private Ficha ficha;

	public Casilla() {
		ficha = null;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) throws OperationNotSupportedException {
		if (ficha == null) {
			throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
		} else if (estaOcupada()) {
			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
		} else {
			this.ficha = ficha;
		}
	}

	public boolean estaOcupada() {
		if (ficha != null) {
			return true;
		} else
			return false;
	}

	@Override
	public String toString() {
		if (ficha != null) {
			return String.format("%c", ficha.name().charAt(0));
		} else
			return " ";
	}

}
