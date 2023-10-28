package org.tesis.xs.serv;

import org.tesis.xs.entity.ClassEntity;
import org.tesis.xs.entity.full.ClassFullEntity;

public interface ClassDao  {
	
	ClassFullEntity initialData() throws Throwable;
	ClassEntity createClass(ClassEntity entity) throws Throwable;
	ClassEntity updateClass(ClassEntity entity) throws Throwable;
}
