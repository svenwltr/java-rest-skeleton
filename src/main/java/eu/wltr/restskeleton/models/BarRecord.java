package eu.wltr.restskeleton.models;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import eu.wltr.restskeleton.rest.mapper.FooField;
 
@Document(collection = "bar")
public class BarRecord {
	
	@Id
	public ObjectId id;
 
	
	public String name;
	public int count;
	
	public Date date;
	
	public FooField foo;

}
