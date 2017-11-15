package com.ipartek.formacion.canciones.controller.canciones;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipartek.formacion.canciones.modelo.dao.UsuarioDAO;

/**
 * Servlet implementation class CheckUserController
 */
@WebServlet(description = "Controlador para perticiones ajax, comprueba la esistencias de usuarios en la base de datos", urlPatterns = {
		"/checkUser" })
public class CheckUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static UsuarioDAO usuarioDao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		usuarioDao = UsuarioDAO.getInstance();

	}

	@Override
	public void destroy() {
		super.destroy();
		usuarioDao = null;

	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// responder en formato json
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		// recoger parametros
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");

		// jsonObject
		Gson gson = new Gson();
		ResponseCheckUser pojo = null;

		if (nombre != null) {

			if (usuarioDao.findByName(nombre)) {
				pojo = new ResponseCheckUser("Usuario No Disponible", false);
			} else {
				pojo = new ResponseCheckUser("Usuario Disponible", true);
			}

		}

		if (email != null) {

			if (usuarioDao.findByEmail(email)) {
				pojo = new ResponseCheckUser("Email No Disponible", false);
			} else {
				pojo = new ResponseCheckUser("Email Disponible", true);
			}

		}

		out.println(gson.toJson(pojo).toString());
		out.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
