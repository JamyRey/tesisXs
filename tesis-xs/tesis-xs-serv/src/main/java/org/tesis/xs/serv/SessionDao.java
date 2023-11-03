package org.tesis.xs.serv;

import org.tesis.xs.entity.LoginEntity;
import org.tesis.xs.entity.SessionEntity;
import org.tesis.xs.exception.BasicException;
import org.tesis.xs.exception.IdentityNotfoundException;

import jakarta.inject.Named;

@Named
public interface SessionDao {
	
    /**
     * Iniciar Sesion
     * @param {@link LoginEntity} Entidad con username y password
     * @return {@link SessionEntity} Sesión con datos y permisos de usuario
     * @throws XFatalException
     * @throws XIdentityNotfoundException
     */
    SessionEntity login(LoginEntity login) throws BasicException, IdentityNotfoundException;

	/**
	 * Cerrar sesi&oacute;n para un usuario.
	 * @param userId Id del usuario
	 * @throws XFatalException
	 */
	void logout(int userId) throws BasicException;
}
