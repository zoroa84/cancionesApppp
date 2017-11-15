package com.ipartek.formacion.canciones.controller.canciones;

/**
 * Pojo para las alertas por pantalla para el usuario de la App.<br>
 *
 * @see https://getbootstrap.com/docs/3.3/components/#alerts-dismissible
 * @author ur00
 *
 */
public class Alert {

	public static final String TIPO_SUCCESS = "success";
	public static final String TIPO_INFO = "info";
	public static final String TIPO_WARNING = "warning";
	public static final String TIPO_DANGER = "danger";

	private String tipo;
	private String texto;

	public Alert() {
		super();
		this.tipo = TIPO_DANGER;
		this.texto = "ERROR INESPERADO!!! sentimos las molestias";
	}

	public Alert(String tipo, String texto) {
		super();
		this.tipo = tipo;
		this.texto = texto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "Alert [tipo=" + tipo + ", texto=" + texto + "]";
	}

}
