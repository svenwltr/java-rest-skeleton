package eu.wltr.restskeleton.mapper;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class CustomObjectMapper extends ObjectMapper {
	public CustomObjectMapper() {
        CustomSerializerFactory sf = new CustomSerializerFactory();
        sf.addSpecificMapping(FooField.class, new FooFieldSerializer());
        this.setSerializerFactory(sf);
    }
}
