package com.macroproyectos.cargue.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion {

	/**
	 * Metodo que permite la conexion a base de datos por meido del data source
	 * 
	 * @return conn. conexion a base de datos
	 */
	public Connection conectarbd() {
		// Objeto para la conexion a base de datos
		Connection conn = null;
		// manejo de excepcion en caso de presentar un error al conectar a la base de
		// datos
		try {
			Context ctx = new InitialContext();
			// Conexion con el datasource del servidor
			DataSource ds = (DataSource) ctx.lookup("java:/OracleDS");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		// retorno de la conexion a base de datos
		return conn;
	}

}
