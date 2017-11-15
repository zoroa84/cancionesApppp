package com.ipartek.formacion.canciones.controller.canciones;

public class ResponseCheckUser {
	private String mensaje;
	private boolean disponible;

	public ResponseCheckUser() {
		super();
		this.mensaje = "";
		this.disponible = false;
	}

	public ResponseCheckUser(String mensaje, boolean disponible) {
		this();
		this.mensaje = mensaje;
		this.disponible = disponible;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		return "ResponseCheckUser [mensaje=" + mensaje + ", disponible=" + disponible + "]";
	}

}
