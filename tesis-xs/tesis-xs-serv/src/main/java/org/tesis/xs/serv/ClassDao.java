package org.tesis.xs.serv;

import org.tesis.xs.entity.ClassEntity;
import org.tesis.xs.entity.full.ClassFullEntity;
import org.tesis.xs.exception.BasicException;

public interface ClassDao  {
	
	ClassFullEntity initialData() throws BasicException,Throwable;
	ClassEntity createClass(ClassEntity entity) throws BasicException,Throwable;
	ClassEntity updateClass(ClassEntity entity) throws BasicException,Throwable;
}
