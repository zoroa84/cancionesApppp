package com.ipartek.formacion.canciones.controller.canciones.backoffice;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.canciones.controller.canciones.Alert;
import com.ipartek.formacion.canciones.modelo.dao.RolesDAO;
import com.ipartek.formacion.canciones.modelo.dao.UsuarioDAO;
import com.ipartek.formacion.canciones.modelo.pojo.Rol;
import com.ipartek.formacion.canciones.pojo.Usuario;
//import com.ipartek.formacion.canciones.modelo.pojo.Usuario;

/**
 * Servlet implementation class CancionesController
 */
@WebServlet("/backoffice/usuarios")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int accion = Acciones.LISTAR;
	private UsuarioDAO daoUsuario;
	private RolesDAO daoRol;

	private Alert alert; // Alertas para el usuario
	private String view; // vista para el forward
	private static final String VIEW_INDEX = "usuarios/index.jsp";
	private static final String VIEW_FORM = "usuarios/form.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoUsuario = UsuarioDAO.getInstance();
		daoRol = RolesDAO.getInstance();
	}

	@Override
	public void destroy() {
		super.destroy();
		daoUsuario = null;
		daoRol = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			alert = null;

			// determinar la accion a realizar
			accion = (request.getParameter("accion") != null) ? Integer.parseInt(request.getParameter("accion"))
					: Acciones.LISTAR;

			switch (accion) {
			case Acciones.LISTAR:
				listar(request);
				break;
			case Acciones.ELIMINAR:
				eliminar(request);
				break;
			case Acciones.MOSTRAR_FORMULARIO:
				mostrarFormulario(request);
				break;
			case Acciones.CREAR_MODIFICAR:
				crearModificar(request);
				break;
			case Acciones.BUSCAR:
				buscar(request);
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

	/**
	 * Busca usuarios por la coincidencia del 'nombre' o 'email'
	 *
	 * @param request
	 */
	private void buscar(HttpServletRequest request) {
		String criterio = request.getParameter("criterio");
		request.setAttribute("listado", daoUsuario.findByNameOrEmail(criterio));
		view = VIEW_INDEX;

	}

	private void crearModificar(HttpServletRequest request) {
		// Por seguridad los cogemos de la session del usuario, nunca de la Vista
		int id = 0;
		int rol_id = 0;

		/*
		 * int id = Integer.parseInt(request.getParameter("id"));
		 *
		 * int rol_id = Integer.parseInt(request.getParameter("rol_id"));
		 */

		// recoger parametros formulario
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String nombre = request.getParameter("nombre");
		String avatar = request.getParameter("avatar");

		HttpSession session = request.getSession();
		Usuario uLogeado = (Usuario) session.getAttribute("usuario_logeado");

		if (uLogeado.getRol().getId() == Usuario.ROL_USER) {
			id = uLogeado.getId();
			rol_id = uLogeado.getRol().getId();
			nombre = uLogeado.getNombre();
		} else {
			id = Integer.parseInt(request.getParameter("id"));
			rol_id = Integer.parseInt(request.getParameter("rol_id"));
		}

		Usuario usuario;
		Rol roles = new Rol(rol_id);

		/* try { */
		usuario = new Usuario(id, nombre, pass, email, avatar);
		/*
		 * Rol rol = new Rol(); rol.setId(rol_id); usuario.setRol(rol);
		 */

		if (id == -1) {
			if (daoUsuario.create(usuario)) {
				alert = new Alert(Alert.TIPO_SUCCESS, "Usuario creado con exito");
				listar(request);
			} else {
				alert = new Alert(Alert.TIPO_DANGER, "No se puede crear el usuario");
				request.setAttribute("usuario", usuario);
				view = VIEW_FORM;
			}
		} else {
			if (daoUsuario.update(usuario, id)) {
				alert = new Alert(Alert.TIPO_SUCCESS, "Usuario modificado con exito");
				listar(request);
			} else {
				alert = new Alert(Alert.TIPO_DANGER, "No se puede modificar el usuario");
				request.setAttribute("usuario", usuario);
				view = VIEW_FORM;
			}
		}

		/*
		 * } catch (Exception e) { e.printStackTrace(); // alert = new
		 * Alert(Alert.TIPO_DANGER, "El formato del email " + email + " no // es
		 * correcto"); request.setAttribute("usuario", new Usuario(id, email, pass,
		 * nombre, avatar)); view = VIEW_FORM; }
		 */

		request.setAttribute("usuario", new Usuario(id, nombre, pass, email, avatar, roles));
		view = VIEW_FORM;
	}

	private void mostrarFormulario(HttpServletRequest request) {

		Usuario usuario = null;
		if (request.getParameter("id") == null) {
			if ("1".equals(request.getParameter("perfil"))) { // Modificar perfil del usuario conectado
				usuario = (Usuario) request.getSession().getAttribute("usuario_logeado");
				usuario = daoUsuario.findById(usuario.getId());
			} else {
				usuario = new Usuario(); // Crear nuevo usuario
			}
		} else { // Modificar datos de cualquier usuario
			int id = Integer.parseInt(request.getParameter("id"));
			usuario = daoUsuario.findById(id);
		}

		/*
		 * usuario = new Usuario(); } else { int id =
		 * Integer.parseInt(request.getParameter("id")); usuario =
		 * daoUsuario.findById(id); }
		 *
		 */
		request.setAttribute("roles", daoRol.findAll());
		request.setAttribute("usuario", usuario);
		view = VIEW_FORM;
	}

	private void eliminar(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));

		if (daoUsuario.delete(id)) {
			alert = new Alert(Alert.TIPO_SUCCESS, "Eliminado con exito el Usuario(" + id + ")");
		} else {
			alert = new Alert(Alert.TIPO_DANGER, "NO se eliminó el usuario(" + id + ")");
		}

		listar(request);
	}

	private void listar(HttpServletRequest request) {
		request.setAttribute("listado", daoUsuario.findAll());
		view = VIEW_INDEX;
	}

}
