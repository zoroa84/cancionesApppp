package com.ipartek.formacion.canciones.modelo.pojo;

public class Artista {
	
	private int id;
	private String nombre;
	
	
	public Artista() {
		super();
		this.id = -1;
		this.nombre = "";
	}
	
	public Artista(int id, String nombre) {
		this();
		this.id = id;
		this.nombre = nombre;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Artista [id=" + id + ", nombre=" + nombre + "]";
	}


	
	
}
