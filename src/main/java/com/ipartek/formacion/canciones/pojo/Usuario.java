package com.ipartek.formacion.canciones.pojo;

//import com.ipartek.formacion.canciones.excepciones.UsuarioException;
import com.ipartek.formacion.canciones.modelo.pojo.Rol;
//import com.ipartek.formacion.canciones.utilidades.Utilidades;

public class Usuario {

	private int id;
	private String nombre;
	private String pass;
	private String email;
	private String avatar;
	private Rol rol;
	public static final int ROL_ADMIN = 1;
	public static final int ROL_USER = 2;

	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "";
		this.pass = "";
		this.email = "";
		this.avatar = "";
		this.rol = new Rol();
	}

	public Usuario(int id) {
		this();
		this.id = id;
		this.nombre = "";
	}

	public Usuario(int id, String nombre) {
		this();
		this.id = id;
		this.nombre = nombre;
		;
	}

	public Usuario(String nombre, String pass, String email) {
		this();
		this.id = -1;
		this.nombre = nombre;
		this.pass = pass;
		this.email = email;
	}

	public Usuario(int id, String nombre, String pass, String email) {
		this();
		this.id = id;
		this.nombre = nombre;
		this.pass = pass;
		this.email = email;
	}

	public Usuario(int id, String nombre, String pass, String email, String avatar) {
		this();
		this.id = id;
		this.nombre = nombre;
		this.pass = pass;
		this.email = email;
		this.avatar = avatar;
	}

	public Usuario(int id, String nombre, String pass, String email, String avatar, Rol rol) {
		this();
		this.id = id;
		this.nombre = nombre;
		this.pass = pass;
		this.email = email;
		this.avatar = avatar;
		this.rol = rol;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", pass=" + pass + ", email=" + email + ", avatar=" + avatar
				+ ", rol=" + rol + "]";
	}

	public void setRol(int usuarioMockRol) {
		// TODO Auto-generated method stub

	}

	// TODO Crear UsuarioException y validarEmail en Utilidades
	/*
	 * public void setEmail(String email) throws UsuarioException {
	 *
	 * if (!Utilidades.validarEmail(email)) {
	 *
	 * throw new CancionException(CancionException.EXCEPTION_DURACION_INCORRECTA); }
	 *
	 * this.email = email; }
	 */

}
