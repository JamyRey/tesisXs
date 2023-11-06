package org.tesis.xs.serv;

import java.sql.Connection;

import org.tesis.xs.exception.BasicException;
import org.tesis.xs.exception.UnauthorizedException;


/**
 * Dao para operaciones con token de autenticaci&pacute;n.
 */
public interface UserTokenDao {
    /**
     * @param token El token por el cual se consulta
     * @return Si el token existe y est&aacute; vigente.
     * @throws XUnauthorizedException Si no hay usuario con el token especificado
     * @throws XFatalException En caso de otro tipo de errores
     */
	boolean validateToken(String token) throws UnauthorizedException, BasicException;

	/**
	 * Crear un token para un usuario
	 * @param userId Identificador del usuario
	 * @param {@link Connection} conexion BD
	 * @return El token generado
	 * @throws XFatalException En caso de error
	 */
	String createToken(int userId, Connection conn) throws BasicException;

	/**
	 * Eliminar el token del usuario
	 * @param userId Id del usuario
	 * @throws XFatalException
	 */
	void clearToken(int userId) throws BasicException;
}
