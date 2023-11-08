package org.tesis.xs.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.tesis.xs.entity.BasicEntity;

public enum ScoresTypes {
	
	sum    (1, "Suma"),
	sub    (2, "Resta"),
	mul    (3, "Multiplicacion"), 
	div    (4, "División"),
	;
	
	public final int id;
	public final String name;
	
	
	private ScoresTypes(int id, String name) {
		this.id = id;
		this.name = name;
	}
	

	public int getId() {
		return id;
	}
	
    public String getName() {
        return name;
    }
    
    public static ScoresTypes byId(int id) {
        for (ScoresTypes en : values())
            if (en.id == id)
                return en;
        throw new RuntimeException("Unknown ScoresTypes");
    }
    
    public static List<BasicEntity> getList() {
        return Arrays.asList(ScoresTypes.values()) 
                .stream().map(e -> new BasicEntity(e.id, e.name))
                                .collect(Collectors.toList());
    }
		
}
