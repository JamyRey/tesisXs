package org.tesis.xs.manager.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

import org.tesis.xs.config.db.DriverManager;
import org.tesis.xs.enums.TimeoutTime;
import org.tesis.xs.exception.BasicException;
import org.tesis.xs.exception.UnauthorizedException;
import org.tesis.xs.serv.UserTokenDao;

public class UserTokenDaoImp implements UserTokenDao {

    @Override
    public boolean validateToken(String token) throws UnauthorizedException, BasicException {
        
        String sql = 
                  " UPDATE usr SET access_token_expiration = dateadd(mi, CAST(? AS INT), getDate())  "
                + " FROM User_login usr "
                + " WHERE access_token = ? AND access_token_expiration > getDate()";
        
        try(Connection conn = DriverManager.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql)) {
            
        	pstm.setInt(1, TimeoutTime.defaultTime.seconds);
            pstm.setString(2, token);
            int updated = pstm.executeUpdate();

            return updated > 0;
            
        } catch (Throwable e) {
            throw new BasicException("Error inesperado validando token "+token,e);
        }
        
    }

    @Override
    public String createToken(int userId, Connection conn) throws BasicException {

        String sql = 
                " UPDATE usr SET access_token = ?, "
              + "     access_token_expiration = dateadd(mi, CAST(? AS INT), getDate())  "
              + " FROM User_login usr "
              + " WHERE usr.id = ?";
      
      try(PreparedStatement pstm = conn.prepareStatement(sql)) {
          
          String token = generateToken();
          
          pstm.setString(1, token);
          pstm.setInt(2, TimeoutTime.defaultTime.seconds);
          pstm.setInt(3, userId);
          pstm.execute();

          return token;
          
      } catch (Throwable e) {
          throw new BasicException("Error inesperado creando token para userId "+userId,e);
      }
        
    }
    
    /**
     * @return Token generado
     */
    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void clearToken(int userId) throws BasicException {
        
        String sql = 
                " UPDATE usr SET access_token = NULL, "
              + "     access_token_expiration = NULL  "
              + " FROM User_login usr "
              + " WHERE usr.id = ?";
      
      try(Connection conn = DriverManager.getConnection();
              PreparedStatement pstm = conn.prepareStatement(sql)) {
          
          pstm.setInt(1, userId);
          pstm.execute();

      } catch (Throwable e) {
          throw new BasicException("Error inesperado limpiando token para userId "+userId,e);
      }

    }

}
