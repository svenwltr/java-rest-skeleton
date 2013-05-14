package eu.wltr.restskeleton.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection = "users")
public class SomeModel {
	
	@Id
	public String id;
 
	
	public String name;
	public int count;
	
	public Date date;

}
