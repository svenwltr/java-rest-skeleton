package eu.wltr.restskeleton.mapper;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class RestObjectMapper extends ObjectMapper {
	public RestObjectMapper() {
        CustomSerializerFactory sf = new CustomSerializerFactory();
        
        sf.addSpecificMapping(FooField.class, new FooFieldSerializer());
        sf.addSpecificMapping(ObjectId.class, new ObjectIdSerializer());
        
        this.setSerializerFactory(sf);
    }
}
