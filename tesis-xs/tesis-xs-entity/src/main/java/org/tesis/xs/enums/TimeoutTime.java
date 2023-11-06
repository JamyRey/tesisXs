package org.tesis.xs.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.tesis.xs.entity.BasicEntity;

public enum TimeoutTime {

	defaultTime    (1, 15*60),
	oneHour    (2, 60*60),
	;
	
	public final int id;
	public final int seconds;
	
	private TimeoutTime(int id, int seconds) {
		this.id = id;
		this.seconds = seconds;
	}
	

	public int getId() {
		return id;
	}
	
    public int getSeconds() {
        return seconds;
    }
    
    public static TimeoutTime byId(int id) {
        for (TimeoutTime en : values())
            if (en.id == id)
                return en;
        throw new RuntimeException("Unknown TimeoutTime");
    }
    
    public static List<BasicEntity> getList() {
        return Arrays.asList(TimeoutTime.values()) 
                .stream().map(e -> new BasicEntity(e.id, String.valueOf(e.seconds)))
                                .collect(Collectors.toList());
    }
		
	
}
