package com.ipartek.formacion.canciones.modelo.pojo;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.ipartek.formacion.canciones.modelo.ConnectionManager;

//import com.ipartek.formacion.canciones.modelo.pojo.Reporte;
public class ReporteDAO {
	

		private static ReporteDAO INSTANCE = null;

		private ReporteDAO() {
			super();
		}

		public synchronized static ReporteDAO getInstance() {

			if (INSTANCE == null) {
				INSTANCE = new ReporteDAO();
			}
			return INSTANCE;
		}
		
		/**
		 * Datos estadisticos de los likes 
		 * @see com.ipartek.formacion.canciones.modelo.pojo.Reporte;
		 * @return Reporte
		 */
	public Reporte getLikes() {
		
		Reporte resul = new Reporte();
		String sql = "{ CALL pa_likes (?, ?, ?, ?, ?) }";
		try (	Connection con = ConnectionManager.open();
				CallableStatement cs = con.prepareCall(sql);){
			
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.registerOutParameter(2, java.sql.Types.INTEGER);
			cs.registerOutParameter(3, java.sql.Types.INTEGER);
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.registerOutParameter(5, java.sql.Types.INTEGER);

			//ResultSet rs = cs.executeQuery();
			
			cs.executeUpdate();
			
			
				resul.setTotal(cs.getInt(1));
				resul.setAnyo(cs.getInt(2));
				resul.setMes(cs.getInt(3));
				resul.setSemana(cs.getInt(4));
				resul.setDia(cs.getInt(5));
	
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			
		}
		
		return resul;
	}

}
