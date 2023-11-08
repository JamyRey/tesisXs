package org.tesis.xs.manager.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tesis.xs.config.db.DriverManager;
import org.tesis.xs.entity.ClassEntity;
import org.tesis.xs.entity.GameSession;
import org.tesis.xs.entity.SessionScore;
import org.tesis.xs.entity.StudentEntity;
import org.tesis.xs.entity.full.StudentFullEntity;
import org.tesis.xs.enums.ScoresTypes;
import org.tesis.xs.exception.BasicException;
import org.tesis.xs.exception.MasterException;
import org.tesis.xs.exception.MasterExceptionEnum;
import org.tesis.xs.serv.StudentDao;

public class StudentDaoImp implements StudentDao{

	private Logger log = LogManager.getLogger(this.getClass());

	@Override
	public StudentFullEntity initialData()throws BasicException{
		try(Connection conn = DriverManager.getConnection()){
			StudentFullEntity result = new StudentFullEntity();
			result.setStudents(getStudentList(conn));


			return result;
		}
		catch (Throwable e) {
			log.debug(e);
			throw new BasicException("Error consultando data inicial para modulo de estudiantes",e);
		}
	}

	
	private List<StudentEntity> getStudentList(Connection conn) throws Throwable {

		StringBuilder query = new StringBuilder("SELECT id, student_id, status_id, first_name, last_name")
				.append(" FROM Students WHERE status_id <> 0");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {


			try(ResultSet rs = pstm.executeQuery()){

				List<StudentEntity> list = new ArrayList<>();

				while(rs.next()) {
					StudentEntity entity = new StudentEntity();
					
					entity.setId(rs.getInt("id"));
					entity.setFirstName(rs.getString("first_name"));
					entity.setLastName(rs.getString("last_name"));
					entity.setStudentId(rs.getInt("student_id"));
					entity.setStatus(rs.getInt("status_id"));
					
					list.add(entity);
				}
				
				return list;
			}

		}
		catch (Throwable e) {
			throw new Throwable("Error consultando lista de estudiantes.",e);
		}

	}
	
	private boolean existStudent(StudentEntity entity,Connection con) throws Throwable{

		String query = "  SELECT id, student_id FROM Students "
				+ "WHERE status_id <> 0 AND id <> ? AND student_id = ?";
		try(PreparedStatement pstm = con.prepareStatement(query);){

			pstm.setInt(1, entity.getId());
			pstm.setInt(2, entity.getStudentId());

			try(ResultSet rs = pstm.executeQuery();){
				return  rs.next();
			}
		}catch (Throwable e) {
			throw new Throwable("Error verificando si existencia del alumno",e);
		}
	}
	
	@Override
	public StudentEntity createStudent(StudentEntity entity) throws BasicException {

		try(Connection conn = DriverManager.getConnection()){

			if(existStudent(entity, conn)) {
				throw MasterExceptionEnum.recordAlreadyExists.exception();
			}
			
			String sql = "INSERT INTO Students "
					+ " (student_id, status_id, first_name, last_name) "
					+ " VALUES(?, 1, ?, ?);";

			try(PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setInt(1, entity.getStudentId());
				pstm.setString(2, entity.getFirstName());
				pstm.setString(3, entity.getLastName());
					
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
			throw new BasicException("Error creando estudiante");
		}

	}

	@Override
	public StudentEntity updateStudent(StudentEntity entity) throws BasicException {

		try(Connection conn = DriverManager.getConnection()){
			
			if(existStudent(entity, conn)) {
				throw MasterExceptionEnum.nameAlreadyExists.exception();
			}

			String sql = "UPDATE Students "
					+ "SET student_id=?, status_id=?, first_name=?, last_name=? "
					+ "WHERE id=?;";

			try(PreparedStatement pstm = conn.prepareStatement(sql)) {

				pstm.setInt(1, entity.getStudentId());
				pstm.setInt(2, entity.getStatus());
				pstm.setString(3, entity.getFirstName());
				pstm.setString(4, entity.getLastName());
				pstm.setInt(5, entity.getId());

				pstm.execute();
			}
		}catch (MasterException e) {        	
			throw e;
		}catch (BasicException e) {        	
			throw e;
		}catch (Throwable e) {        	
			throw new BasicException("Error actualizando estudiante ");
		}



		return entity;
	}
	
	@Override
	public StudentEntity getStudentById(int id) throws BasicException {
		
		try(Connection conn = DriverManager.getConnection()){
			
			StudentEntity student = getStudentById(id, conn);
	
			return student;
			
		}catch (BasicException e) {        	
			throw e;
		}catch (Throwable e) {
			throw new BasicException("Error consultando estudiante por id");
		}
		
	}
	
	private StudentEntity getStudentById(int id, Connection conn) throws Throwable {

		StringBuilder query = new StringBuilder("SELECT id, student_id, status_id, first_name, last_name")
				.append(" FROM Students")
				.append(" WHERE id = ?");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {
			pstm.setInt(1, id);

			try(ResultSet rs = pstm.executeQuery()){
				if(rs.next()) {
					StudentEntity entity = new StudentEntity();
					entity.setId(rs.getInt("id"));
					entity.setFirstName(rs.getString("first_name"));
					entity.setLastName(rs.getString("last_name"));
					entity.setStudentId(rs.getInt("student_id"));
					entity.setStatus(rs.getInt("status_id"));
					
					entity.setSessions(getGameSessions(id, conn));
					entity.setClasses(getClassByStudent(id, conn));

					return entity;
				}else {
					throw new Throwable("No encontrada la clase");
				}
			}
		}catch (Throwable e) {
			throw new Throwable("Error consultando clase por id");
		}
	}
	
	private List<GameSession> getGameSessions(int id, Connection conn) throws Throwable {
		List<GameSession> list = new ArrayList<GameSession>();
		StringBuilder query = new StringBuilder("SELECT DISTINCT gs.id, gs.game_id, gs.start_time, gs.end_time, c.id cid, c.name ")
				.append(" FROM Game_session gs ")
				.append(" INNER JOIN Session_scores ss ON gs.id = ss.game_session_id ")
				.append(" INNER JOIN Class_by_game_session cgs ON gs.id = cgs.game_session_id")
				.append(" INNER JOIN Class c ON cgs.class_id = c.id")
				.append(" INNER JOIN Students s on ss.student_id = s.id ")
				.append(" WHERE s.id = ? ");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {
			pstm.setInt(1, id);

			try(ResultSet rs = pstm.executeQuery()){
				
				while(rs.next()) {

					GameSession entity = new GameSession();
					entity.setId(rs.getInt("id"));
					entity.setStartAt(rs.getObject("start_time", LocalDateTime.class));
					entity.setEndendAt(rs.getObject("end_time", LocalDateTime.class));
					ClassEntity c = new ClassEntity();
					c.setId(rs.getInt("cid"));
					c.setName(rs.getString("name"));
					entity.setClassE(c);
					entity.setScores(getSessionScores(entity.getId(), conn));
					list.add(entity);
				}
			}

			return list;


		}catch (Throwable e) {
			throw new Throwable("Error consultando clase por id");
		}
	}
	
	private List<SessionScore> getSessionScores(int id, Connection conn) throws Throwable {
		List<SessionScore> scores = new ArrayList<SessionScore>();
		StringBuilder query = new StringBuilder("SELECT ss.[type], ss.is_valid")
				.append(" FROM Session_scores ss ")
				.append(" INNER JOIN Game_session gs ON gs.id = ss.game_session_id ")
				.append(" WHERE gs.id = ? ");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {
			pstm.setInt(1, id);

			try(ResultSet rs = pstm.executeQuery()){
				
				while(rs.next()) {
						SessionScore ss = new SessionScore(ScoresTypes.byId(rs.getInt("type")),rs.getBoolean("is_valid"));
						scores.add(ss);
				}
				return scores;				
			}
		}catch (Throwable e) {
			throw new Throwable("Error consultando clase por id");
		}
	}
	
	private List<ClassEntity> getClassByStudent(int id, Connection conn) throws Throwable {

		StringBuilder query = new StringBuilder("SELECT c.id, c.name ")
				.append(" FROM Class c")
				.append(" INNER JOIN Class_by_student cs ON c.id = cs.class_id")
				.append(" WHERE c.status_id <> 0 AND cs.student_id = ?");

		try(PreparedStatement pstm = conn.prepareStatement(query.toString())) {

			pstm.setInt(1, id);

			try(ResultSet rs = pstm.executeQuery()){

				List<ClassEntity> list = new ArrayList<>();

				while(rs.next()) {
					ClassEntity entity = new ClassEntity();
					entity.setId(rs.getInt("id"));
					entity.setName(rs.getString("name"));
					
					list.add(entity);
				}				
				return list;
			}

		}
		catch (Throwable e) {
			throw new Throwable("Error consultando lista de clases por estudiantes.",e);
		}

	}
	
	@Override
    public void deleteStudent(int id) throws BasicException {

    	try(Connection conn = DriverManager.getConnection()) {

    		String sql = 
    				" UPDATE Students "
    						+ " SET status_id = 0 "
    						+ " WHERE ID = ?";

    		try(PreparedStatement pstm = conn.prepareStatement(sql)) {

    			pstm.setInt(1, id);

    			pstm.execute();

    		}
    	}
    	catch (Throwable e) {
    		throw new BasicException("Error borrando estudiante. (Id = "+id+").",e);
    	}

    }

}
