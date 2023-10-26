package org.tesis.xs.config.jersey;

import java.net.URI;
import java.util.HashMap;

import org.tesis.util.jakarta.net.rest.jerset.client.JerseyClientGenery;
import org.tesis.util.jakarta.net.rest.jerset.client.JerseyMethodEnum;
import org.tesis.util.jakarta.net.rest.jerset.client.JerseyServiceInterface;

public class JerseyClient extends JerseyClientGenery implements AutoCloseable {
	
	public JerseyClient(String url,JerseyMethodEnum method,
			JerseyServiceInterface service) {
		super(method, service, url);
	}
	
	public JerseyClient(String url, JerseyMethodEnum method,
            HashMap<String, String> parameters) {
        super(method, parameters, url);
    }

	public JerseyClient(String url, JerseyMethodEnum method,
			JerseyServiceInterface service, HashMap<String, String> parameters) {
	    super(method, service, parameters, url);
	}
	
	
	@Override
	protected URI serverURI() {
		return null;
	}

}
