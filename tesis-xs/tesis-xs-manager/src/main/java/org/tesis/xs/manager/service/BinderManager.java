package org.tesis.xs.manager.service;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.tesis.xs.manager.imp.ClassDaoImp;
import org.tesis.xs.serv.ClassDao;

/**
 * Binder de inyecci&oacute;n para todas las instancias del subproyecto
 */
public class BinderManager extends AbstractBinder {
    
    @Override
    protected void configure() {
    	
    	bind(ClassDaoImp.class).to(ClassDao.class);
    	
    }

}
