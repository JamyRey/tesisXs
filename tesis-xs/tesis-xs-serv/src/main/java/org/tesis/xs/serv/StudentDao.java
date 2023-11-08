package org.tesis.xs.serv;

import org.tesis.xs.entity.StudentEntity;
import org.tesis.xs.entity.full.StudentFullEntity;
import org.tesis.xs.exception.BasicException;

public interface StudentDao {
	
	StudentFullEntity initialData() throws BasicException;
	StudentEntity createStudent(StudentEntity entity) throws BasicException;
	StudentEntity updateStudent(StudentEntity entity) throws BasicException;
	StudentEntity getStudentById(int id) throws BasicException;
	void deleteStudent(int id) throws BasicException;
	
}
