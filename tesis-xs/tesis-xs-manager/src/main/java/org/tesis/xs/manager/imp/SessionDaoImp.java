package org.tesis.xs.manager.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.TimeZone;

import org.tesis.xs.config.db.DriverManager;
import org.tesis.xs.entity.LoginEntity;
import org.tesis.xs.entity.SessionEntity;
import org.tesis.xs.exception.BasicException;
import org.tesis.xs.exception.IdentityNotfoundException;
import org.tesis.xs.exception.MasterException;
import org.tesis.xs.exception.MasterExceptionEnum;
import org.tesis.xs.serv.SessionDao;
import org.tesis.xs.serv.UserTokenDao;

public class SessionDaoImp implements SessionDao {

    //@Inject private TokenDao tokenDao;
    
    private UserTokenDao tokenDao = new UserTokenDaoImp();
    
    @Override
    public SessionEntity login(LoginEntity login) throws BasicException, IdentityNotfoundException {
        
        
        SessionEntity session = null;
        
    	try (Connection conn = DriverManager.getConnection()){
    	    
    	    String sql = 
                    " SELECT ul.id,  ul.userName, ul.status_id  "
                  + " FROM User_login ul  "
                  + " WHERE ul.userName = ? AND ul.password = ?";
    	    
    	    try(PreparedStatement pstm = conn.prepareStatement(sql)) {
    	        
    	        pstm.setString(1, login.getUsername());
    	        pstm.setString(2, login.getPassword());
    	        
    	        try(ResultSet rs = pstm.executeQuery()) {
    	            if(rs.next()) {
    	                
    	                if(rs.getInt("STATUS_ID") != 1/**MasterStatusEnum.active.getId()**/)
    	                	throw MasterExceptionEnum.recordDisable.exception(); 
    	                	
    	                
    	                session = new SessionEntity();
    	                session.setUserId(rs.getInt("id"));
    	                session.setUserName(rs.getString("userName"));
    	                session.setTimeOutSession(60*60);
    	                session.setLocale(new Locale("es", "VE"));
    	                session.setTimezone(TimeZone.getTimeZone("America/Caracas"));
    	                session.setToken(tokenDao.createToken(session.getUserId(), conn));
    	                
    	            }
    	            else 
    	                throw MasterExceptionEnum.recordNotFound.exception();     
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
