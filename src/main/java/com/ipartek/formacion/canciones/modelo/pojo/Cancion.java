package com.ipartek.formacion.canciones.modelo.pojo;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.canciones.excepciones.CancionException;
import com.ipartek.formacion.canciones.pojo.Usuario;
import com.ipartek.formacion.canciones.utilidades.Utilidades;

public class Cancion {

	private int id;
	private String titulo;
	private String artista;
	private String duracion;
	private String cover;
	private Genero genero;
	private Usuario usuario;
	private String alta;
	private String modificacion;
	private List<Artista> artistas;
	private int likes;

	public Cancion() {
		super();
		this.id = -1;
		this.likes = 0;
		this.titulo = "";
		this.artista = "";
		this.duracion = "0:00";
		this.cover = "";
		this.genero = new Genero();
		this.usuario = new Usuario();
		this.alta = "";
		this.modificacion = "";
		this.artistas = new ArrayList<Artista>();
	}

	public Cancion(int id, String titulo, String artista, String cover) {
		this();
		this.id = id;
		this.titulo = titulo;
		this.artista = artista;
		this.cover = cover;
	}

	public Cancion(int id, String titulo, String artista, String duracion, String cover) throws CancionException {
		this();
		this.id = id;
		this.titulo = titulo;
		this.artista = artista;
		this.setDuracion(duracion);
		this.cover = cover;
	}

	public Cancion(String titulo, String artista, String duracion) throws CancionException {
		this();
		this.titulo = titulo;
		this.artista = artista;
		this.setDuracion(duracion);
		this.cover = "";
	}

	public Cancion(String titulo, String artista, String duracion, String cover) throws CancionException {
		this(titulo, artista, duracion);
		this.cover = cover;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	public void addArtista(Artista artista) {
		if (this.artistas == null) {
			this.artistas = new ArrayList<Artista>();
		}
		this.artistas.add(artista);
	}

	public String getAlta() {
		return alta;
	}

	public void setAlta(String alta) {
		this.alta = alta;
	}

	public String getModificacion() {
		return modificacion;
	}

	public void setModificacion(String modificacion) {
		this.modificacion = modificacion;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getDuracion() {
		return duracion;
	}

	public int getDuracionSegundos() {
		int seg = -1;
		try {
			String[] trozos = this.duracion.split(":");
			int minutos = Integer.parseInt(trozos[0]) * 60;
			int segundos = Integer.parseInt(trozos[1]);
			seg = minutos + segundos;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return seg;
	}

	/**
	 * La duracion de una Cancion debe tener el siguiente formato: [0]0:00
	 *
	 * @param duracion
	 *            throws CancionException Formato de duracion no correcto [0]0:00
	 */

	public void setDuracion(String duracion) throws CancionException {

		if (!Utilidades.validarDuracion(duracion)) {

			throw new CancionException(CancionException.EXCEPTION_DURACION_INCORRECTA);
		}

		this.duracion = duracion;
	}

	public int getLikes() {

		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Cancion [id=" + id + ", titulo=" + titulo + ", artista=" + artista + ", duracion=" + duracion
				+ ", cover=" + cover + ", genero=" + genero + ", usuario=" + usuario + ", alta=" + alta
				+ ", modificacion=" + modificacion + ", artistas=" + artistas + ", likes=" + likes + "]";
	}

}