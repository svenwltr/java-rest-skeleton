package eu.wltr.restskeleton.fields.mapper;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.springframework.stereotype.Controller;

import eu.wltr.restskeleton.fields.Duration;
import eu.wltr.restskeleton.fields.DurationSerializer;

@Controller
public class RestObjectMapper extends ObjectMapper {
	public RestObjectMapper() {
		CustomSerializerFactory sf = new CustomSerializerFactory();

		sf.addSpecificMapping(Duration.class, new DurationSerializer());

		this.setSerializerFactory(sf);

	}
}
