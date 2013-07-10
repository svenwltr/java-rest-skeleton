package eu.wltr.restskeleton.models;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import eu.wltr.restskeleton.fields.Duration;

@Document
public class TimeDocument {

	@Id
	public String id;

	@Indexed(unique = true)
	@NotBlank
	public String name;

	public Date date;

	public Duration duration;

}
