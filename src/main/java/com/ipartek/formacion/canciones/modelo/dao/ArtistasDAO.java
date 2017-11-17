package com.ipartek.formacion.canciones.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.canciones.excepciones.CancionException;
import com.ipartek.formacion.canciones.modelo.ConnectionManager;
import com.ipartek.formacion.canciones.modelo.pojo.Artista;
import com.ipartek.formacion.canciones.modelo.pojo.Cancion;
import com.ipartek.formacion.canciones.pojo.Usuario;

public class ArtistasDAO implements Persistable<Artista> {

	private static ArtistasDAO INSTANCE = null;

	private ArtistasDAO() {
		super();
	}

	public synchronized static ArtistasDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ArtistasDAO();
		}
		return INSTANCE;
	}

	@Override
	public boolean create(Artista artista) {
		boolean resul = false;
		String sql = "INSERT INTO `canciones`.`artista` (`nombre`) VALUES (?);";

		try (Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		) {

			pst.setString(1, artista.getNombre());

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				// recuperar el Identificador generado
				try (ResultSet keys = pst.getGeneratedKeys();) {
					while (keys.next()) {
						artista.setId(keys.getInt(1));
					}
					resul = true;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return resul;
	}

	@Override
	public boolean update(Artista artista, int id) {
		boolean resul = false;
		String sql = "UPDATE `artista` SET `nombre`=? WHERE  `id`=?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, artista.getNombre());
			pst.setInt(2, id);

			if (pst.executeUpdate() == 1) {
				resul = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		String sql = "DELETE FROM `artista` WHERE  `id`=?;";
		try (

				Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql);

		) {

			pst.setInt(1, id);
			if (pst.executeUpdate() == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public List<Artista> findAll() {
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		String sql = "SELECT `id`, `nombre` FROM `artista` ORDER BY `id` desc;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				Artista artista;
				while (rs.next()) {
					artista = mapeo(rs);
					artistas.add(artista);
					artista = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return artistas;
	}

	@Override
	public Artista findById(int id) {
		Artista artista = null;
		String sql = "SELECT `id`, `nombre` FROM `artista` WHERE `id` = ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					artista = mapeo(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return artista;
	}

	/**
	 *
	 *
	 * recuperar todos los artistas de una cancion
	 *
	 * @param idCancion
	 *            identificador de a cancion
	 * @return los artistas encontrado, si no encuentra lista vacia
	 *
	 *
	 */

	public List<Cancion> findAllByUser(Usuario uLogeado) {

		ArrayList<Cancion> canciones = new ArrayList<Cancion>();

		String sql = "select c.titulo, c.duracion from usuario as u, cancion as c where c.usuario_id = u.id and u.id = ?;";
		if (uLogeado != null) {
			uLogeado.getRol().getId();
			try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

				pst.setInt(1, uLogeado.getId());
				try (ResultSet rs = pst.executeQuery();) {
					while (rs.next()) {
						Cancion c = new Cancion();
						c.setTitulo(rs.getString("titulo"));
						c.setCover(rs.getString("cover"));

						// mapear campos que faltan

						canciones.add(c);

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}

		}
		return canciones;

	}

	Artista mapeo(ResultSet rs) throws SQLException, CancionException {
		Artista artista = null;
		if (rs != null) {
			artista = new Artista();
			artista.setId(rs.getInt("a.id"));
			artista.setNombre(rs.getString("nombre"));
		}
		return artista;
	}

}
