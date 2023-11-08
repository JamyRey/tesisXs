package org.tesis.xs.manager.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tesis.xs.config.db.DriverManager;
import org.tesis.xs.entity.BasicEntity;
import org.tesis.xs.entity.ClassEntity;
import org.tesis.xs.entity.GameSession;
import org.tesis.xs.entity.StudentEntity;
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
			result.setGames(getGameList(conn));

			return result;
		}
		catch (Throwable e) {
			log.debug(e);
			throw new Throwable("Error consultando data inicial para modulo de sucursales",e);
		}
	}

	
	private List<ClassEntity> getClassList(Connection conn) throws Throwable {

		StringBuilder query = new StringBuilder("SELECT c.id, c.name, c.status_id, c.is_active, cg.game_id, g.name gName ")
				.append(" FROM Class c "
						+ " LEFT JOIN Class_by_game cg ON c.id = cg.class_id"
						+ " LEFT JOIN Games g ON cg.game_id = g.id"
						+ " WHERE c.status_id <> 0");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {


			try(ResultSet rs = pstm.executeQuery()){

				List<ClassEntity> list = new ArrayList<>();

				while(rs.next()) {
					ClassEntity entity = new ClassEntity();
					entity.setId(rs.getInt("id"));
					entity.setName(rs.getString("name"));
					entity.setStatus(rs.getInt("status_id"));
					entity.setActiveClass(rs.getBoolean("is_active"));
					entity.setGame(rs.getInt("game_id"));
					entity.setGameName(rs.getString("gName"));
					
					list.add(entity);
				}
				
				return list;
			}

		}
		catch (Throwable e) {
			throw new Throwable("Error consultando lista de clases.",e);
		}

	}
	
	private List<BasicEntity> getGameList(Connection conn) throws Throwable {

		StringBuilder query = new StringBuilder("SELECT id, name ")
				.append(" FROM Games WHERE status_id <> 0");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {


			try(ResultSet rs = pstm.executeQuery()){

				List<BasicEntity> list = new ArrayList<>();

				while(rs.next()) {
					BasicEntity entity = new ClassEntity();
					entity.setId(rs.getInt("id"));
					entity.setName(rs.getString("name"));
					list.add(entity);
				}
				
				return list;
			}

		}
		catch (Throwable e) {
			throw new Throwable("Error consultando lista de clases.",e);
		}
	}
	
	private List<BasicEntity> getStudentList(int id, Connection conn) throws Throwable {

		StringBuilder query = new StringBuilder("SELECT s.id, s.first_name, s.last_name")
				.append(" FROM Students s")
				.append(" INNER JOIN Class_by_student cbs ON s.id = cbs.student_id")
				.append(" WHERE s.status_id <> 0 AND cbs.class_id = ?");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {

			pstm.setInt(1, id);

			try(ResultSet rs = pstm.executeQuery()){

				List<BasicEntity> list = new ArrayList<>();

				while(rs.next()) {
					BasicEntity entity = new StudentEntity();
					
					entity.setId(rs.getInt("id"));
					entity.setName(rs.getString("first_name").concat(" "+rs.getString("last_name")));
					
					list.add(entity);
				}
				
				return list;
			}

		}
		catch (Throwable e) {
			throw new Throwable("Error consultando lista de estudiantes.",e);
		}

	}
	
	private boolean existClassName(ClassEntity entity,Connection con) throws Throwable{

		String query = "  SELECT id, name FROM  Class "
				+ "WHERE status_id <> 0 AND id <> ? AND name = ?";
		try(PreparedStatement pstm = con.prepareStatement(query);){

			pstm.setInt(1, entity.getId());
			pstm.setString(2, entity.getName());

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

			if(existClassName(entity, conn)) {
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
				manageGameRelation(entity, 0, conn);
				
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
	public ClassEntity updateClass(ClassEntity entity) throws BasicException {

		try(Connection conn = DriverManager.getConnection()){
			
			if(existClassName(entity, conn)) {
				throw MasterExceptionEnum.nameAlreadyExists.exception();
			}

			String sql = "UPDATE Class "
					+ " SET name=?, status_id=?, is_active=? "
					+ " WHERE id=?;";

			try(PreparedStatement pstm = conn.prepareStatement(sql)) {

				pstm.setString(1, entity.getName());
				pstm.setInt(2, entity.getStatus());
				pstm.setBoolean(3, entity.isActiveClass());
				pstm.setInt(4, entity.getId());

				pstm.execute();
				
				manageGameRelation(entity, 1, conn);
			}
		}catch (MasterException e) {        	
			throw e;
		}catch (BasicException e) {        	
			throw e;
		}catch (Throwable e) {        	
			throw new BasicException("Error actualizando clase ");
		}



		return entity;
	}
	
	public void manageGameRelation(ClassEntity entity,int action, Connection con) throws Throwable{
		try(Connection conn = DriverManager.getConnection()){

			if(action==0) {

				String sql = "INSERT INTO Class_by_game "
						+ " (class_id, game_id) "
						+ " VALUES(?, ?);";

				try(PreparedStatement pstm = conn.prepareStatement(sql)) {

					pstm.setInt(1, entity.getId());
					pstm.setInt(2, entity.getGame());

					pstm.execute();
				}
			}else {
				String sql = "UPDATE gs "
						+ " SET Class_by_game=? "
						+ " FROM Class_by_game as gs"
						+ " where gs.class_id = ?";

				try(PreparedStatement pstm = conn.prepareStatement(sql)) {

					pstm.setInt(1, entity.getGame());
					pstm.setInt(2, entity.getId());

					pstm.execute();
				}
			}
		}catch (Throwable e) {        	
			throw new BasicException("Error manejando relacion del juego a la clase ");
		}
	}
	
	@Override
	public ClassEntity getClassById(int id) throws BasicException {
		
		try(Connection conn = DriverManager.getConnection()){
			
			ClassEntity classEntity = getClassById(id, conn);
	
			return classEntity;
			
		}catch (BasicException e) {        	
			throw e;
		}catch (Throwable e) {
			throw new BasicException("Error consultando clase por id");
		}
		
	}
	
	private ClassEntity getClassById(int id, Connection conn) throws Throwable {

		StringBuilder query = new StringBuilder(" SELECT c.id,c.name, c.status_id, c.is_active, cg.game_id ")
				.append(" FROM Class c ")
				.append(" LEFT JOIN Class_by_game cg ON c.id = cg.class_id ")
				.append(" WHERE c.id = ?");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {
			pstm.setInt(1, id);

			try(ResultSet rs = pstm.executeQuery()){
				if(rs.next()) {
					ClassEntity entity = new ClassEntity();
					entity.setId(rs.getInt("id"));
					entity.setName(rs.getString("name"));
					entity.setStatus(rs.getInt("status_id"));
					entity.setActiveClass(rs.getBoolean("is_active"));
					entity.setGame(rs.getInt("game_id"));
					
					entity.setStudents(getStudentList(id, conn));

					return entity;
				}else {
					throw new Throwable("No encontrada la clase");
				}
			}
		}catch (Throwable e) {
			throw new Throwable("Error consultando clase por id");
		}
	}
	
	@Override
	public void updateActiveClass(ClassEntity entity) throws BasicException {

		try(Connection conn = DriverManager.getConnection()){
			if(entity.isActiveClass())
				deActiveAllClass(entity);
			else {
				createGamesession(entity);
			}
			String sql = "UPDATE Class "
					+ " SET is_active=? "
					+ " WHERE id=?;";

			try(PreparedStatement pstm = conn.prepareStatement(sql)) {
				pstm.setBoolean(1, entity.isActiveClass()?false:true);
				pstm.setInt(2, entity.getId());
				pstm.execute();
			}
			
		}catch (BasicException e) {        	
			throw e;
		}catch (Throwable e) {        	
			throw new BasicException("Error actualizando clase ");
		}
	}
	
	private void createGamesession(ClassEntity entity) throws Throwable{
		try(Connection conn = DriverManager.getConnection()){
			String sql = "INSERT INTO  Game_session "
					+ " (game_id, start_time) "
					+ " VALUES(?, getdate());";
			GameSession session = new GameSession();
			try(PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setInt(1, entity.getGame());

				pstm.execute();


				try (ResultSet rs = pstm.getGeneratedKeys()) {
					if (rs.next()) {
						session.setId(rs.getInt(1));
					}
				}	
			}

			sql = "INSERT INTO  Class_by_game_session "
					+ " (class_id, game_session_id) "
					+ " VALUES(?, ?);";

			try(PreparedStatement pstm = conn.prepareStatement(sql)) {

				pstm.setInt(1, entity.getId());
				pstm.setInt(2, session.getId());
				pstm.executeUpdate();				

			}

		}catch (Throwable e) {        	
			throw new BasicException("Error creando sesion de juego ");
		}

	}


	private void deActiveAllClass(ClassEntity entity) throws Throwable {

		try(Connection conn = DriverManager.getConnection()){
			
			String sql = "UPDATE Class "
					+ " SET is_active=0 ";

			try(PreparedStatement pstm = conn.prepareStatement(sql)) {
				pstm.execute();
			}
			
			sql = "UPDATE gs "
					+ " SET end_time=getdate() "
					+ " FROM Game_session as gs"
					+ " INNER JOIN Class_by_game_session as cgs ON gs.id = cgs.game_session_id"
					+ " where cgs.class_id = ?";

			try(PreparedStatement pstm = conn.prepareStatement(sql)) {
				
				pstm.setInt(1, entity.getId());
				
				pstm.execute();
			}
			
			sql = "UPDATE Game_session "
					+ " SET end_time= case when end_time is null then getdate() else end_time end";

			try(PreparedStatement pstm = conn.prepareStatement(sql)) {
				
				pstm.execute();
			}
			
		}catch (Throwable e) {        	
			throw new BasicException("Error desactivando clases y sesiones de juego ");
		}
	}
	
	@Override
    public void deleteClass(int id) throws BasicException {

    	try(Connection conn = DriverManager.getConnection()) {

    		String sql = 
    				" UPDATE Class "
    						+ " SET status_id = 0 "
    						+ " WHERE ID = ?";

    		try(PreparedStatement pstm = conn.prepareStatement(sql)) {

    			pstm.setInt(1, id);

    			pstm.execute();

    		}
    	}
    	catch (Throwable e) {
    		throw new BasicException("Error borrando clase. (Id = "+id+").",e);
    	}

    }

}
