package com.ipartek.formacion.canciones.modelo.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.canciones.pojo.Usuario;

public class TestUsuarioDAO {

	static UsuarioDAO usuarioDAO;
	static Usuario usuarioMock;
	static final String USUARIO_MOCK_NOMBRE = "mock123sdfasdfa6s5df46as";
	static final String USUARIO_MOCK_PASS = "mock123sdfasdfa6s5df46as@sadadsf.com";
	static final String USUARIO_MOCK_EMAIL = "asdflkjoihaofhds";
	static final int USUARIO_MOCK_ROL = Usuario.ROL_USER;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		usuarioDAO = UsuarioDAO.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		usuarioDAO = null;
	}

	@Before
	public void setUp() throws Exception {
		usuarioMock = new Usuario();
		usuarioMock.setNombre(USUARIO_MOCK_NOMBRE);
		usuarioMock.setEmail(USUARIO_MOCK_EMAIL);
		usuarioMock.setPass(USUARIO_MOCK_PASS);
		usuarioMock.setRol(USUARIO_MOCK_ROL);

		assertTrue(usuarioDAO.create(usuarioMock));
	}

	@After
	public void tearDown() throws Exception {
		assertTrue(usuarioDAO.delete(usuarioMock.getId()));
		usuarioMock = null;
	}

	@Test
	public void findByName() {
		assertTrue(usuarioDAO.findByName(USUARIO_MOCK_NOMBRE));
		assertTrue("deberia hacer trim (eliminar espacios en blanco)",
				usuarioDAO.findByName("   " + USUARIO_MOCK_NOMBRE + "    "));
		assertTrue("deberia mostrarlo en minunculas", usuarioDAO.findByName(USUARIO_MOCK_NOMBRE.toLowerCase()));
		assertTrue("deberia mostrarlo en mayusculas", usuarioDAO.findByName(USUARIO_MOCK_NOMBRE.toUpperCase()));

		assertFalse("null", usuarioDAO.findByName(null));
		assertFalse(usuarioDAO.findByName(USUARIO_MOCK_NOMBRE + "3"));
	}

}
