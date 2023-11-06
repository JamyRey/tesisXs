package org.tesis.xs.config.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Utilitario para conseguir el DataSource a usar para la comunicaci&oacute;n
 * con la base de datos
 */
public class DriverManager {
	/** @return El DataSource por defecto de la aplicaci&oacute;n */
	public static DataSource getDataSource() throws NamingException {
			return getDataSource("jdbc/tesisxs");
    }

    /**
     * @return Una conexi&oacute;n sacada de {@link #getDataSource()}
     * @throws NamingException En caso de error de conexi&oacute;n.
     * @throws SQLException En caso de error de acceso a la bd
     */
    public static Connection getConnection() throws NamingException, SQLException {
        return getDataSource().getConnection();
    }
	
	public static DataSource getDataSource(String jndi) throws NamingException {
		DataSource dataSource = null;		
		try {
		dataSource = (DataSource)(new InitialContext()).lookup("java:comp/env/" + jndi);
		}catch (NamingException e) {
		}
		return dataSource;
	}
	
}
