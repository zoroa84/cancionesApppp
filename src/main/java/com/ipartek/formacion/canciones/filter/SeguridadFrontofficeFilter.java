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
 * Comprueba que el usuario este en session para dejarle pasar.<br>
 * Si no esta en session redirect a login.jsp
 *
 *
 * @WebFilter("/frontoffice/*")
 */
public class SeguridadFrontofficeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SeguridadFrontofficeFilter() {

	}

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
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
