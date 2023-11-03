package org.tesis.xs.manager.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import org.tesis.xs.config.db.DriverManager;
import org.tesis.xs.entity.LoginEntity;
import org.tesis.xs.entity.SessionEntity;
import org.tesis.xs.exception.BasicException;
import org.tesis.xs.exception.IdentityNotfoundException;
import org.tesis.xs.exception.MasterException;
import org.tesis.xs.exception.MasterExceptionEnum;
import org.tesis.xs.serv.SessionDao;

import jakarta.inject.Inject;

public class SessionDaoImp implements SessionDao {

    //@Inject private TokenDao tokenDao;
    
    //TODO cambiar por inject
    //private UserTokenDao tokenDao = new UserTokenDaoImp();
    
    @Override
    public SessionEntity login(LoginEntity login) throws BasicException, IdentityNotfoundException {
        
        
        SessionEntity session = null;
        
    	try (Connection conn = DriverManager.getConnection()){
    	    
    	    String sql = 
                    " SELECT tsu.ID, tsu.FIRST_NAME, tsu.LAST_NAME, "
                  + "     tsu.EMAIL, tsu.STATUS_ID, tsu.REGISTRATION_COMPLETED  "
                  + " FROM T_SYS_USER tsu  "
                  + " WHERE tsu.EMAIL = ? AND tsu.PASSWORD = ?";
    	    
    	    try(PreparedStatement pstm = conn.prepareStatement(sql)) {
    	        
    	        pstm.setString(1, login.getUsername());
    	        pstm.setString(2, login.getPassword());
    	        
    	        try(ResultSet rs = pstm.executeQuery()) {
    	            if(rs.next()) {
    	                
    	                if(rs.getInt("STATUS_ID") != 1/**MasterStatusEnum.active.getId()**/)
    	                	throw MasterExceptionEnum.recordDisable.exception(); 
    	                	//throw new XIdentityNotAvailableException("Usuario inhabilidado");
    	                
    	                session = new SessionEntity();    	             
    	                session.setTimeOutSession(60*60);
    	                // TODO Obtener de base de datos
    	                session.setLocale(new Locale("es", "VE"));
    	                session.setTimezone(TimeZone.getTimeZone("America/Caracas"));
    	                //session.setToken(tokenDao.createToken(session.getUserId(), conn));
    	                
    	            }
    	            else 
    	                throw MasterExceptionEnum.recordNotFound.exception(); 
    	                //new XIdentityNotfoundException("Usuario no encontrado");
    	        }
    	        
    	    }
    	    
	        return session;
    	}catch (MasterException e) {
			throw e;
		}catch (BasicException e) {
			throw e;
		} catch (Throwable e) {
			throw new BasicException("Error inesperado haciendo login ",e);
		}
    }
    

    @Override
    public void logout(int userId) throws BasicException {

        try {
            tokenDao.clearToken(userId);
        } catch (BasicException e) {
            throw e;
        } catch (Exception e) {
            throw new BasicException("Error en logout: ",e);
        }

    }

}
