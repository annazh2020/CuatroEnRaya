package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Jugador {

	private String nombre;
	private Ficha ficha;

	public Jugador(String nombre, Ficha ficha) {
		setNombre(nombre);
		setColorFichas(ficha);
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");

		}
		this.nombre = nombre;
	}

	public Ficha getColorFichas() {
		return ficha;
	}

	private void setColorFichas(Ficha ficha) {
		if (ficha == null) {
			throw new NullPointerException("ERROR: El color de las fichas no puede ser nulo.");
		} else {
			this.ficha = ficha;
		}

	}

	@Override
	public String toString() {
		return String.format("%s (%s)", nombre, ficha);
	}

}
