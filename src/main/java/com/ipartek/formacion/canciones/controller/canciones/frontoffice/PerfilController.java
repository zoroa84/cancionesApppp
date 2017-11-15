package com.ipartek.formacion.canciones.controller.canciones.frontoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.canciones.controller.canciones.Alert;
import com.ipartek.formacion.canciones.modelo.dao.UsuarioDAO;
import com.ipartek.formacion.canciones.pojo.Usuario;

/**
 * Servlet implementation class PerfilController
 */
@WebServlet("/frontoffice/profile")
public class PerfilController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		Alert alert = null;

		try {

			// parametros formulario
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			String repass = request.getParameter("repass");
			String avatar = request.getParameter("avatar");
			boolean cambiarPass = false;
			boolean passCoincide = true;

			if (!"".equals(repass)) {
				if (!repass.equals(pass)) {
					alert = new Alert(Alert.TIPO_WARNING, "Las contraseñas no coinciden");
					passCoincide = false;
				} else {
					cambiarPass = true;
				}
			}

			// cambiar en session
			if (passCoincide) {
				HttpSession session = request.getSession();
				Usuario uLogeado = (Usuario) session.getAttribute("usuario_logeado");

				uLogeado.setAvatar(avatar);
				uLogeado.setEmail(email);
				if (cambiarPass) {
					uLogeado.setPass(pass);
				}
				session.setAttribute("usuario_logeado", uLogeado);

				// cambiar en BBDD
				UsuarioDAO dao = UsuarioDAO.getInstance();
				if (dao.update(uLogeado, uLogeado.getId())) {
					alert = new Alert(Alert.TIPO_INFO, "Cambios realizados con exito");
				} else {
					alert = new Alert(Alert.TIPO_DANGER, "ERROR cambiando el perfil");
				}

			}

		} catch (Exception e) {

		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher("/frontoffice/profile.jsp").forward(request, response);
		}

	}

}
