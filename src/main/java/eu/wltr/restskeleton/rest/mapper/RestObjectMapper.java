package eu.wltr.restskeleton.rest.mapper;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.springframework.stereotype.Controller;

import eu.wltr.restskeleton.models.ModelBase;



@Controller
public class RestObjectMapper extends ObjectMapper
{
	public RestObjectMapper() {
		CustomSerializerFactory sf = new CustomSerializerFactory();

		sf.addGenericMapping(ModelBase.class, new ModelSerializer());

		sf.addSpecificMapping(FooField.class, new FooFieldSerializer());

		this.setSerializerFactory(sf);
	}
}
