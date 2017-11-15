package com.ipartek.formacion.canciones.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.canciones.excepciones.CancionException;
import com.ipartek.formacion.canciones.modelo.ConnectionManager;
import com.ipartek.formacion.canciones.modelo.pojo.Rol;
//import com.ipartek.formacion.canciones.modelo.pojo.Usuario;
import com.ipartek.formacion.canciones.pojo.Usuario;

public class UsuarioDAO implements Persistable<Usuario> {

	private static UsuarioDAO INSTANCE = null;

	private UsuarioDAO() {
		super();
	}

	public synchronized static UsuarioDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}
		return INSTANCE;
	}

	/**
	 * Busca usuarios que coincidan el nombre o email del usuario
	 * <code>Usuario</code>.<br>
	 * No es casesentive.<br>
	 *
	 * @param criterio
	 *            String cadena a buscar
	 * @return Listado de usuarios limitado por <code>LIMIT</code>, vacia si no
	 *         encuentra nada
	 */
	public List<Usuario> findByNameOrEmail(String criterio) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT `id`, `email`, `pass`, `nombre`, `avatar` FROM `usuario` WHERE  `nombre` LIKE ? OR `email` LIKE ? ORDER BY `id` DESC LIMIT ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "%" + criterio + "%");
			pst.setString(2, "%" + criterio + "%");
			pst.setInt(3, Persistable.LIMIT);

			try (ResultSet rs = pst.executeQuery();) {
				Usuario u;
				while (rs.next()) {
					u = mapeo(rs);
					usuarios.add(u);
					u = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return usuarios;
	}

	public Usuario findByNameAndPass(String nombre, String pass) {
		Usuario usuario = null;
		String sql = "SELECT u.id, u.nombre, u.pass, u.email, r.id AS rol_id, r.nombre AS rol_nombre, u.avatar FROM usuario AS u, rol AS r WHERE u.rol_id = r.id AND u.nombre=? AND u.pass=?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, nombre);
			pst.setString(2, pass);

			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					usuario = mapeo(rs);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return usuario;
	}

	@Override
	public boolean create(Usuario u) {
		boolean resul = false;
		String sql = "";
		if (u.getAvatar() == null || "".equals(u.getAvatar())) {
			sql = "INSERT INTO `usuario` (`email`, `pass`, `nombre`, `rol_id`) VALUES (?, ?, ?, ?);";
		} else {
			sql = "INSERT INTO `usuario` (`email`, `pass`, `nombre`, `rol_id`, `avatar`) VALUES (?, ?, ?, ?, ?);";
		}

		try (Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		) {

			pst.setString(1, u.getEmail());
			pst.setString(2, u.getPass());
			pst.setString(3, u.getNombre());

			if (u.getRol() == null) {
				pst.setInt(4, Usuario.ROL_USER);
			} else {
				pst.setInt(4, Usuario.ROL_USER);
			}

			if (u.getAvatar() != null && !"".equals(u.getAvatar())) {
				pst.setString(5, u.getAvatar());
			}

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				// recuperar el Identificador generado
				try (ResultSet keys = pst.getGeneratedKeys();) {
					while (keys.next()) {
						u.setId(keys.getInt(1));
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
	public boolean update(Usuario u, int id) {
		boolean resul = false;
		String sql = "";
		if (u.getAvatar() == null || "".equals(u.getAvatar())) {
			sql = "UPDATE `usuario` SET `email`='?', `pass`='?', `nombre`='?', `rol_id`=? WHERE  `id`=?;";
		} else {
			sql = "UPDATE `usuario` SET `email`='?', `pass`='?', `avatar`='?', `rol_id`=?, `nombre`='?' WHERE  `id`=?;";
		}

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, u.getEmail());
			pst.setString(2, u.getPass());
			pst.setString(3, u.getNombre());
			pst.setInt(4, u.getRol().getId());

			if (u.getAvatar() == null || "".equals(u.getAvatar())) {
				pst.setInt(5, id);
			} else {
				pst.setString(5, u.getAvatar());
				pst.setInt(6, id);
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
		String sql = "DELETE FROM `usuario` WHERE  `id`=?;";
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
	public List<Usuario> findAll() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT `u`.`id`,`email`,`pass`,`u`.`nombre`, `avatar`, r.id as rol_id,  r.nombre as rol_nombre FROM usuario as u ,`rol` as r WHERE u.rol_id = r.id ORDER BY `u`.`id` DESC LIMIT ?;";
		// String sql = "SELECT `id`, `email`, `pass`, `nombre`, `avatar` FROM `usuario`
		// ORDER BY `id` DESC LIMIT ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, Persistable.LIMIT);

			try (ResultSet rs = pst.executeQuery();) {
				Usuario u;
				while (rs.next()) {
					u = mapeo(rs);
					usuarios.add(u);
					u = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return usuarios;
	}

	@Override
	public Usuario findById(int id) {
		Usuario u = null;
		String sql = "SELECT u.id, u.nombre, pass, email, avatar, r.id as rol_id, r.nombre as rol_nombre FROM usuario as u, rol as r WHERE u.rol_id = r.id AND u.id=?;";
		// String sql = "SELECT `id`, `email`, `pass`, `nombre`, `avatar` FROM `usuario`
		// WHERE `id`=?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					u = mapeo(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return u;
	}

	public boolean findByEmail(String email) {
		boolean resul = true;
		if (email != null) {
			String sql = "{call pa_check_usuario_email(?)}";
			try (Connection con = ConnectionManager.open(); CallableStatement cs = con.prepareCall(sql);) {
				cs.setString(1, email);
				try (ResultSet rs = cs.executeQuery();) {
					if (!rs.next()) {
						resul = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}

		} else {

		}
		return resul;
	}

	/**
	 * buscar un usuario casesensitive y trimar espacios en blanco
	 *
	 * @param nombre
	 *            string a a buscar
	 * @return si lo encuentra true y si no false
	 */
	public boolean findByName(String nombre) {
		boolean resul = true;
		if (nombre != null) {
			String sql = "{call pa_check_usuario_nombre(?)}";
			try (Connection con = ConnectionManager.open(); CallableStatement cs = con.prepareCall(sql);) {
				cs.setString(1, nombre);
				try (ResultSet rs = cs.executeQuery();) {
					if (!rs.next()) {
						resul = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}

		} else {

		}
		return resul;
	}

	/**
	 * Mapea de un ResultSet a un objeto de tipo Usuario
	 *
	 * @param rs
	 *            ResultSet
	 * @return Usuario o null si falla
	 * @throws SQLException
	 * @throws CancionException
	 *             si la duración no tiene formato adecuado @see Usuario.setDuracion
	 */
	Usuario mapeo(ResultSet rs) throws SQLException, CancionException {
		Usuario u = null;
		if (rs != null) {
			u = new Usuario();
			u.setId(rs.getInt("id"));
			u.setEmail(rs.getString("email"));
			u.setPass(rs.getString("pass"));
			u.setNombre(rs.getString("nombre"));
			u.setAvatar(rs.getString("avatar"));
			// rol
			Rol r = new Rol();
			r.setId(rs.getInt("rol_id"));
			r.setNombre(rs.getString("rol_nombre"));
			u.setRol(r);

		}
		return u;
	}

}
