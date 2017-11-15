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
import com.ipartek.formacion.canciones.modelo.pojo.Cancion;
import com.ipartek.formacion.canciones.modelo.pojo.Genero;
import com.ipartek.formacion.canciones.pojo.Usuario;

public class CancionDAO implements Persistable<Cancion> {

	private static CancionDAO INSTANCE = null;

	private CancionDAO() {
		super();
	}

	public synchronized static CancionDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new CancionDAO();
		}
		return INSTANCE;
	}

	/**
	 * Busca canciones que coincidan el nombre o artista de la
	 * <code>Cancion</code>.<br>
	 * No es keysesentive.<br>
	 *
	 * @param criterio
	 *            String cadena a buscar
	 * @return Listado de Canciones limitado por <code>LIMIT</code>, vacia si no
	 *         encentra nada
	 */
	public List<Cancion> findByNameOrArtist(String criterio) {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		String sql = "SELECT `id`, `nombre`, `artista`, `duracion`, `cover` FROM `cancion` WHERE  `nombre` LIKE ? OR `artista` LIKE ? ORDER BY `id` DESC LIMIT ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "%" + criterio + "%");
			pst.setString(2, "%" + criterio + "%");
			pst.setInt(3, Persistable.LIMIT);

			try (ResultSet rs = pst.executeQuery();) {
				Cancion c;
				while (rs.next()) {
					c = mapeo(rs);
					canciones.add(c);
					c = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return canciones;
	}

	@Override
	public List<Cancion> findAll() {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		String sql = "SELECT `c`.`id`, `titulo`,`duracion`, `c`.`cover`, `g`.`id` as genero_id,  g.nombre as genero_nombre , u.id as usuario_id, u.nombre as usuario_nombre  , c.fecha_modificacion as modificacion, c.fecha_creacion as alta  FROM usuario as u ,cancion as c , genero as g   WHERE c.usuario_id = u.id AND c.genero_id = g.id  ORDER BY `c`.`id` DESC LIMIT ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, Persistable.LIMIT);

			try (ResultSet rs = pst.executeQuery();) {
				Cancion c;
				while (rs.next()) {
					c = mapeo(rs);
					canciones.add(c);
					c = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return canciones;
	}

	@Override
	public boolean create(Cancion c) {
		boolean resul = false;
		String sql = "";
		if (c.getCover() == null || "".equals(c.getCover())) {
			sql = "INSERT INTO `cancion` (`titulo`, `duracion`,`genero_id`) VALUES (?, ?, ?);";
		} else {
			sql = "INSERT INTO `cancion` (`titulo`, `duracion`, `genero_id`,`cover`) VALUES (?, ?, ?, ?);";
		}

		try (Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		) {

			pst.setString(1, c.getNombre());
			pst.setString(2, c.getDuracion());
			pst.setInt(3, c.getGenero().getId());

			if (c.getCover() != null && !"".equals(c.getCover())) {
				pst.setString(4, c.getCover());
			}

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				// recuperar el Identificador generado
				try (ResultSet keys = pst.getGeneratedKeys();) {
					while (keys.next()) {
						c.setId(keys.getInt(1));
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
	public boolean update(Cancion c, int id) {
		boolean resul = false;
		String sql = "";
		if (c.getCover() == null || "".equals(c.getCover())) {
			sql = "UPDATE `cancion` SET `titulo`=?, `genero_id`=?, `duracion`=?  WHERE  `id`=?;";
		} else {
			sql = "UPDATE `cancion` SET `titulo`=?, `genero_id`=?, `duracion`=?, `cover`=? WHERE  `id`=?;";
		}

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, c.getNombre());
			pst.setInt(2, c.getGenero().getId());
			pst.setString(3, c.getDuracion());

			if (c.getCover() == null || "".equals(c.getCover())) {
				pst.setInt(4, id);
			} else {
				pst.setString(4, c.getCover());
				pst.setInt(5, id);
			}

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
		String sql = "DELETE FROM `cancion` WHERE  `id`=?;";
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
	public Cancion findById(int id) {
		Cancion c = null;
		String sql = "SELECT  u.id as usuario_id,u.nombre as usuario_nombre, c.id, titulo, duracion, cover, g.id as genero_id, g.nombre as genero_nombre , c.fecha_modificacion as modificacion, c.fecha_creacion as alta  FROM usuario u, cancion as c, genero as g WHERE u.id = c.usuario_id AND c.genero_id = g.id AND c.id = ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					c = mapeo(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return c;
	}

	/**
	 * Mapea de un ResultSet a un objeto de tipo Cancion
	 *
	 * @param rs
	 *            ResultSet
	 * @return Cancion o null si falla
	 * @throws SQLException
	 * @throws CancionException
	 *             si la duración no tiene formato adecuado @see Cancion.setDuracion
	 */
	Cancion mapeo(ResultSet rs) throws SQLException, CancionException {
		Cancion c = null;
		if (rs != null) {
			c = new Cancion();
			c.setId(rs.getInt("id"));  //"c.id"
			c.setNombre(rs.getString("titulo"));
			c.setAlta(rs.getString("alta"));
			c.setModificacion(rs.getString("modificacion"));

			c.setDuracion(rs.getString("duracion"));
			c.setCover(rs.getString("cover"));
			// genero
			Genero g = new Genero();
			g.setId(rs.getInt("genero_id"));
			g.setNombre(rs.getString("genero_nombre"));
			c.setGenero(g);
			// usuario
			Usuario u = new Usuario();
			//g.setId(rs.getInt("genero_id"));  //AQUI NO SERIA u.setId(rs.getInt("usuario_id"));  ??
			u.setId(rs.getInt("usuario_id"));
			u.setNombre(rs.getString("usuario_nombre"));
			c.setUsuario(u);

		}
		return c;
	}

	public int dameLikes(int id) {
		int resul = 0;
		String sql = "select count(l.cancion_id) as likes from likes as l, cancion as c where l.cancion_id =c.id and c.id = ?";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					resul = rs.getInt("likes");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return resul;
	}

	public List<Cancion> findAllByUser(Usuario uLogeado) {

		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		if (uLogeado != null) {
			uLogeado.getRol().getId();
		}
		return canciones;
	}

}
