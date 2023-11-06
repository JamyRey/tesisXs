package org.tesis.xs.entity;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Objeto con todos los datos necesarios en la sesion
 */
@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionEntity implements Serializable {

	private int userId;
	private String 	 userName;
    private String   token;
    private Locale   locale;
    private TimeZone timezone;
    private int 	 timeOutSession;
    
    public SessionEntity() {
        super();
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public TimeZone getTimezone() {
		return timezone;
	}

	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}

	public int getTimeOutSession() {
		return timeOutSession;
	}

	public void setTimeOutSession(int timeOutSession) {
		this.timeOutSession = timeOutSession;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}
