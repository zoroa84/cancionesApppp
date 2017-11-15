package com.ipartek.formacion.canciones.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.canciones.pojo.Usuario;

/**
 * Comprueba que el usuario este en session <br>
 * Si no esta en session redirect a login.jsp<br>
 * Si esta en session pero no tiene permisos de ADMIN redirect a listado
 * canciones.<br>
 *
 * 
 * @WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = {
 *                            "/backoffice/*" })
 */
public class SeguridadSessionFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		System.out.println("Filtrando: " + httpRequest.getRequestURL());

		/// comprobar que haya hecho login
		HttpSession session = httpRequest.getSession();
		Usuario u = (Usuario) session.getAttribute("usuario_logeado");
		if (u == null) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp?msg=Debes+estar+logeado");

		} else {

			if (u.getRol().getId() == Usuario.ROL_ADMIN) {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			} else {
				// TODO meter en un LOGGER
				System.out.println("Usuario sin permisos " + u);
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/frontoffice/canciones");
			}

		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
