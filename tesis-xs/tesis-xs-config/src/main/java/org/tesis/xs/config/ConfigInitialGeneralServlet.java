package org.tesis.xs.config;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name ="ConfigInitialGeneralServlet", urlPatterns = "/load/configGeneral", loadOnStartup = 0)
public class ConfigInitialGeneralServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger(this.getClass());
	
	public static String REAL_PATH;
	
	public static String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
	
	public static boolean        loadOk;
	
	 @Override
	    public void init(ServletConfig config) throws ServletException {
	        super.init(config);
	        try {

	            REAL_PATH = config.getServletContext().getRealPath("/");

	            log.info("Servidor XS backend Iniciado");
	            System.out.println("Servidor XS backend Iniciado");
	            loadOk = true;

	        }
	        catch (Throwable e) {
	            log.error("Error", e);
	        }

	    }
}
