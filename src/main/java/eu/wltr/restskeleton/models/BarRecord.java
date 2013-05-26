package eu.wltr.restskeleton.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import eu.wltr.restskeleton.rest.mapper.FooField;
 
@Document(collection = "bar")
public class BarRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;


	@Id
	public String id;
 
	
	public String name;
	public int count;
	
	public Date date;
	
	public FooField foo;

}
