package org.tesis.xs.manager.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tesis.xs.config.db.DriverManager;
import org.tesis.xs.entity.ClassEntity;
import org.tesis.xs.entity.full.ClassFullEntity;
import org.tesis.xs.exception.BasicException;
import org.tesis.xs.exception.MasterException;
import org.tesis.xs.exception.MasterExceptionEnum;
import org.tesis.xs.serv.ClassDao;

public class ClassDaoImp implements ClassDao{

	private Logger log = LogManager.getLogger(this.getClass());

	@Override
	public ClassFullEntity initialData()throws Throwable{
		try(Connection conn = DriverManager.getConnection()){
			ClassFullEntity result = new ClassFullEntity();
			result.setClasses(getClassList(conn));


			return result;
		}
		catch (Throwable e) {
			log.debug(e);
			throw new Throwable("Error consultando data inicial para modulo de sucursales",e);
		}
	}

	
	private List<ClassEntity> getClassList(Connection conn) throws Throwable {

		StringBuilder query = new StringBuilder("SELECT id, name, status_id, is_active ")
				.append(" FROM Class");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {


			try(ResultSet rs = pstm.executeQuery()){

				List<ClassEntity> list = new ArrayList<>();

				while(rs.next()) {
					ClassEntity entity = new ClassEntity();
					entity.setId(rs.getInt("id"));
					entity.setName(rs.getString("name"));
					entity.setStatus(rs.getInt("status_id"));
					entity.setActiveClass(rs.getBoolean("is_active"));
					
					list.add(entity);
				}
				
				return list;
			}

		}
		catch (Throwable e) {
			throw new Throwable("Error consultando lista de clases.",e);
		}

	}
	
	private boolean existClassName(String name,Connection con) throws Throwable{

		String query = "  SELECT ID FROM  T_SYS_COMPANY_GROUP WHERE XETUX_CODE = ?";
		try(PreparedStatement pstm = con.prepareStatement(query);){

			pstm.setString(1, name);

			try(ResultSet rs = pstm.executeQuery();){

				return  rs.next();

			}
		}catch (Throwable e) {
			throw new Throwable("Error obteniendo id de company group",e);
		}
	}
	@Override
	public ClassEntity createClass(ClassEntity entity) throws BasicException,Throwable {

		try(Connection conn = DriverManager.getConnection()){

			if(existClassName(entity.getName(), conn)) {
				throw MasterExceptionEnum.nameAlreadyExists.exception();
			}
			
			String sql = "INSERT INTO Class "
					+ " ( name, status_id, is_active) "
					+ " VALUES(?, ?, ?);";

			try(PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, entity.getName());
				pstm.setInt(2, entity.getStatus());
				pstm.setBoolean(3, entity.isActiveClass());
					
				pstm.executeUpdate();
				
				try (ResultSet rs = pstm.getGeneratedKeys()) {
					if (rs.next()) {
						entity.setId(rs.getInt(1));
					}
				}
				
			}

			return entity;
		}catch (MasterException e) {        	
			throw e;
		}catch (BasicException e) {        	
			throw e;
		}catch (Throwable e) {        	
			throw new Throwable("Error creando clase");
		}

	}

	@Override
	public ClassEntity updateClass(ClassEntity entity) throws Throwable {

		try(Connection conn = DriverManager.getConnection()){

			String sql = "UPDATE Class "
					+ " SET name=?, status_id=?, is_active=? "
					+ " WHERE id=?;";

			try(PreparedStatement pstm = conn.prepareStatement(sql)) {

				pstm.setString(1, entity.getName());
				pstm.setInt(2, entity.getStatus());
				pstm.setBoolean(3, entity.isActiveClass());
				pstm.setInt(4, entity.getId());
					
				pstm.execute();
				
				
			}catch (Throwable e) {        	
				throw new Throwable("Error actualizando clase ");
			}

		}

		return entity;
	}
	
	public ClassEntity getClassById(int id) throws Throwable {
		
		try(Connection conn = DriverManager.getConnection()){
			
			ClassEntity classEntity = getClassById(id, conn);
			
			
			
			return classEntity;
			
		}catch (Throwable e) {
			throw new Throwable("Error consultando clase por id");
		}
		
	}
	
	private ClassEntity getClassById(int id, Connection conn) throws Throwable {

		StringBuilder query = new StringBuilder("SELECT id, name, status_id, is_active ")
				.append(" FROM Class ")
				.append(" WHERE id = ?");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {


			try(ResultSet rs = pstm.executeQuery()){
				if(rs.next()) {
					ClassEntity entity = new ClassEntity();
					entity.setId(rs.getInt("id"));
					entity.setName(rs.getString("name"));
					entity.setStatus(rs.getInt("status_id"));
					entity.setActiveClass(rs.getBoolean("is_active"));

					return entity;
				}else {
					throw new Throwable("No encontrada la clase");
				}
			}
		}catch (Throwable e) {
			throw new Throwable("Error consultando clase por id");
		}
	}

}
