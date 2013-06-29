package eu.wltr.restskeleton.fields.mapper;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.stereotype.Controller;

import eu.wltr.restskeleton.fields.Duration;
import eu.wltr.restskeleton.fields.DurationDeserializer;
import eu.wltr.restskeleton.fields.DurationSerializer;

@Controller
public class RestObjectMapper extends ObjectMapper {
	public RestObjectMapper() {
		SimpleModule testModule = new SimpleModule("MyModule", new Version(1,
				0, 0, null));
		testModule.addSerializer(new DurationSerializer());
		testModule.addDeserializer(Duration.class, new DurationDeserializer());
		this.registerModule(testModule);

	}
}
