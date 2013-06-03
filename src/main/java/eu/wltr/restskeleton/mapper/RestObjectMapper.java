package eu.wltr.restskeleton.mapper;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class RestObjectMapper extends ObjectMapper {
	public RestObjectMapper() {
		CustomSerializerFactory sf = new CustomSerializerFactory();

		sf.addSpecificMapping(FooField.class, new FooFieldSerializer());

		this.setSerializerFactory(sf);
	}
}
